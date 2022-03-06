import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Allows to play sounds in the "sounds" folder depend on situation
 * @author Arda Gokalp Batmaz
 * @since Date:12.04.2021
 *
 */
public class SoundCall {
	
	/**
	 * This method provide us to playing relevant sound
	 * @param soundName Name of the song which is going to call
	 * @param ms How many miliseconds music going to play
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public void soundList(String soundName, int ms) throws LineUnavailableException
	, IOException, UnsupportedAudioFileException
	{
		Clip clip;
		URL url = this.getClass().getResource("sounds/"+soundName+".wav");// Takes sound from the file
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
		clip = AudioSystem.getClip();

		clip.open(audioIn); 
		clip.start(); // Calls the sound
		Game_Board.waitTime(ms); // Sound calls for specified miliseconds

	}
	/**
	 * Method determines which sound is going to played depend on the parameter
	 * @param sound Name of the sound effect
	 */
	public void playSound(String sound)
	{
		// Depend on sound name calls soundList with an argument and plays a sound
		try {
			if(sound.equals("HitEffect"))
			{
				//hitEffect();
				soundList("HitEffect",5);
			}
			if(sound.equals("WallHitEffect"))
			{
				//wallSound();
				soundList("WallHitEffect",5);
			}
			if(sound.equals("ScoreEffect"))
			{
				//scoreSound();
				soundList("ScoreEffect",0);
			}
			if(sound.equals("CountDownEffect"))
			{
				//countDownSound();
				soundList("CountDownEffect",0);
			}
			if(sound.equals("WinSound"))
			{
				//gameOverEffect();
				soundList("WinSound",0);
			}
			if(sound.equals("ButtonEffect"))
			{
				//gameOverEffect();
				soundList("ButtonEffect",0);
			}
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
