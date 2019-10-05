import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BgMusic {

	public static void Sound(String file, String Options) {
		Clip clip;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream
					(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if (Options=="Loop")
				clip.loop(-1);
			else if(Options=="Stop") {
				clip.stop();
				clip.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
