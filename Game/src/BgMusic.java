import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BgMusic {
	private static Clip clip;
	
	public static void Sound(String file, String Options) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			
			if (Options == "Loop") {
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
				clip.loop(-1);
			} else if (Options == "Stop") {
				clip.flush();
				clip.stop();
				clip.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
