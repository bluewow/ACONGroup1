
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BgmBeeFly {
	private static Clip clip;

	public static void Play() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(
					new BufferedInputStream(new FileInputStream("res/BeeFly.wav")));

			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Stop() {
		clip.flush();
		clip.stop();
		clip.close();
	}
}
