
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BgmMain {
	private static Clip clip;

	public static void Play(String Options) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(
					new BufferedInputStream(new FileInputStream("res/MainBgm.wav")));

			if (Options == "Loop") {
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
				clip.loop(-1);
			}

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
