package Miner;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.Format;

import org.excobot.bot.event.listeners.PaintListener;
import org.excobot.bot.script.GameScript;
import org.excobot.bot.script.Manifest;
import org.excobot.game.api.event.events.MessageEvent;
import org.excobot.game.api.event.listeners.MessageListener;
import org.excobot.game.api.methods.cache.media.Widgets;
import org.excobot.game.api.methods.media.animable.GameObjects;
import org.excobot.game.api.methods.tab.Skills;
import org.excobot.game.api.util.Time;
import org.excobot.game.api.util.Timer;
import org.excobot.game.api.wrappers.media.animable.object.GameObject;

@Manifest(description = "Mine rock you want.", name = "Reds Miner", authors = "Red_Clown")
public class Main extends GameScript implements PaintListener,
		MessageListener {

	public static long startTime = 0;

	private int startExp, startLevel, expGained, expPH, oresMined, minedPH,
			gainedLevel = 0;

	public static int rockID[];
	public static int tileRadius = 0;
	public final static int PICKAXES[] = { 1265, 1267, 1269, 1273, 1271, 1275 };

	private String runTime;
	public static String status = "Waiting for GUI";

	public static Format f = new DecimalFormat("###,###,###");
	private final Font arial = new Font("Arial", 0, 12);
	private final Color transparent = new Color(18, 180, 18, 90);
	Timer updateMath = new Timer(800);

	public static boolean waitingForGUI = true;
	MinerGUI g = new MinerGUI();

	public boolean start() {
		startExp = Skills.MINING.getExperience();
		startLevel = Skills.MINING.getRealLevel();
		g.setVisible(true);
		JobsHandler.addNode(new Mine());
		JobsHandler.addNode(new Drop());
		return true;
	}

	public int execute() {
		while (waitingForGUI) {
			Time.sleep(500);
		}
		if (Widgets.getContinue() != null) {
			Widgets.clickContinue();
		}
		return JobsHandler.run();
	}

	public void onStop() {
	}
	
	
	
	public void repaint(Graphics g) {
		if (waitingForGUI) {
			GameObject rocks[] = GameObjects.getLoaded("Rocks");
			for (GameObject rock : rocks) {
				if (rock != null && rock.isOnScreen()) {
					int x = rock.getLocation().getCentralPoint().x;
					int y = rock.getLocation().getCentralPoint().y;
					g.drawString("" + rock.getId(), x, y);
				}
			}
		}

		if (!updateMath.isRunning()) {
			updateMath();
		}
		drawRect(372, 225, 144, 113, g);
		g.setFont(arial);
		drawText("Exp Gained: " + f.format(expGained), 378, 240, g);
		drawText("Exp Per Hour: " + f.format(expPH), 378, 255, g);
		drawText("Levels Gained: " + gainedLevel, 378, 270, g);
		drawText("Ores Mined: " + f.format(oresMined), 378, 285, g);
		drawText("Mined Per Hour: " + f.format(minedPH), 378, 300, g);
		drawText("Status: " + status, 378, 315, g);
		drawText("Time Ran: " + runTime, 378, 330, g);
	}

	private void updateMath() {
		expGained = Skills.MINING.getExperience() - startExp;
		expPH = perHour(expGained);
		minedPH = perHour(oresMined);
		gainedLevel = Skills.MINING.getRealLevel() - startLevel;
		if (!waitingForGUI) {
			runTime = ranTime(startTime);
		} else {
			runTime = "00:00:00";
		}
		updateMath.reset();
	}

	private int perHour(int calc) {
		return (int) (3600000.0 / (System.currentTimeMillis() - startTime) * calc);
	}

	public void drawText(String string, int x, int y, Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(string, x + 1, y + 1);
		g.setColor(Color.WHITE);
		g.drawString(string, x, y);
	}

	public void drawRect(int x, int y, int h, int w, Graphics g) {
		g.setColor(transparent);
		g.fillRect(x, y, h, w);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, h, w);
	}

	private String ranTime(long i) {
		DecimalFormat d = new DecimalFormat("00");
		long millis = System.currentTimeMillis() - i;
		long hours = millis / (1000 * 60 * 60);
		millis -= hours * (1000 * 60 * 60);
		long minutes = millis / (1000 * 60);
		millis -= minutes * (1000 * 60);
		long seconds = millis / 1000;
		return d.format(hours) + ":" + d.format(minutes) + ":"
				+ d.format(seconds);
	}

	public void messageReceived(MessageEvent m) {
		if (m.getMessage().contains("You manage to")) {
			oresMined++;
		}
	}
}