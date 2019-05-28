package it.maucel89.bigterminal.terminal;

import com.google.common.base.Strings;
import com.liferay.petra.string.StringPool;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mauro Celani
 */
public class TerminalWatcher implements Runnable {

    public TerminalWatcher(InputStream outFromChannel, TextArea textArea) {
        _outFromChannel = outFromChannel;
        _textArea = textArea;
    }

    @Override
    public void run() {

        InputStreamReader isr = new InputStreamReader(_outFromChannel);

        try {
            char[] buff = new char[1024];
            int read;

            while ((read = isr.read(buff)) != -1) {

                String s = new String(buff, 0, read);

                Pattern pattern = Pattern.compile("^(\b+)(.*)");
                Matcher matcher = pattern.matcher(s);

                if (matcher.find()) {
                    String firstBacks = matcher.group(1);
                    s = matcher.group(2);
                    _moveCursorLeft(firstBacks.length(), !Strings.isNullOrEmpty(s));
                }

                boolean eraseLine = false;
                if (s.contains("\u001B[K")) {
                    eraseLine = true;
                }

                boolean middleErase = false;
                int numBacks = 0;
                if (s.contains("\u001B[P")) {
                    middleErase = true;
                    numBacks = s.split("\b").length - 1;
                }

                if (s.equals("\u001B[C")) {
                    _moveCursorRight(1);
                }

                String res = _removeEscapes(s);

                if (!res.isEmpty()) {

                    final boolean finalMiddleErase = middleErase;
                    final int finalNumBacks = numBacks;

                    Platform.runLater(() -> {
                        if (finalMiddleErase) {
                            _textArea.insertText(_textArea.getCaretPosition(), res);
                            _textArea.positionCaret(
                                _textArea.getCaretPosition() - finalNumBacks);
                        }
                        else {
                            _textArea.appendText(res);

                            // SCROLL BOTTOM AFTER OUTPUT
                            _textArea.selectPositionCaret(_textArea.getLength());
                            _textArea.deselect();
                            //_textArea.setScrollTop(Double.MAX_VALUE);
                        }
                    });
                }
                else if (eraseLine) {
                    _eraseLine();
                }
            }
        }
        catch (InterruptedIOException e) {
            _log.debug(e, e);
        }
        catch (IOException e) {
            _log.warn(e, e);
        }
    }

    private String _removeEscapes(String source) {
        return _escapeMap.entrySet()
            .stream()
            .reduce(
                source,
                (s, e) -> s.replaceAll(e.getKey(), e.getValue()),
                (s1, s2) -> null
            );
    }

    private void _eraseLine() {
        Platform.runLater(() -> _textArea.deleteText(
            _textArea.getCaretPosition(), _textArea.getLength()));
    }

    private void _moveCursorRight(int count) {

        _moveCursor(count, false);
    }

    private void _moveCursorLeft(int count, boolean delete) {

        _moveCursor(-count, delete);
    }

    private void _moveCursor(int count, boolean delete) {

        Platform.runLater(() -> {

            _textArea.positionCaret(_textArea.getCaretPosition() + count);

            if (delete) {
                _eraseLine();
            }
        });
    }

    private InputStream _outFromChannel;
    private TextArea _textArea;

    private static final Map<String, String> _escapeMap = new HashMap() {{
        put("\u001B[\\(\\)][AB012]", StringPool.BLANK);
        put("\u001B\\[\\?*\\d*;*\\d*[a-zA-Z]", StringPool.BLANK);
        put("\u001B[><=A-Z]", StringPool.BLANK);
        put("\\[\\d*;*\\d*m", StringPool.BLANK);
        put("\u000F", StringPool.BLANK);
        put("\u0007", StringPool.BLANK);
        put("\b", StringPool.BLANK);
    }};

    private static final Logger _log = Logger.getLogger(TerminalWatcher.class);

}

