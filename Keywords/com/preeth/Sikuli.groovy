import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject

import org.sikuli.script.Button
import org.sikuli.script.FindFailed
import org.sikuli.script.Screen
import org.sikuli.script.Key
import org.sikuli.script.Pattern
import org.sikuli.script.Match
import com.kms.katalon.core.util.KeywordUtil
import org.sikuli.script.KeyModifier
import org.sikuli.script.App

/**
 * @author priyatham.bolli
 *
 */
public class Sikuli{

	public static Screen _screen = new Screen();

	@Keyword
	public static void find(String region) {
		try {
			_screen.find(region);
			KeywordUtil.markPassed("Element " + region + " has been found successfully");
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markPassed("Fail to find the element");
		}
	}

	@Keyword
	public static void click(String region) {
		try {
			_screen.click(region);
			KeywordUtil.markPassed("Element " + region + " has been clicked successfully")
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}


	@Keyword
	public static void sendkey(String label) {
		try {
			_screen.type(label);
			KeywordUtil.markPassed("Typed " + "'" + label + "'" + " and Sendkeys method Passed successfully");
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		}catch (Exception e) {
			KeywordUtil.markFailed("Sendkeys method Failed");
		}
	}


	@Keyword
	public static void exists(String image, double time) {
		try {
			_screen.exists(image, time);
			KeywordUtil.markPassed("'" + image + "'" + " exists and exists method Passed");
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("exists method Failed");
		}
	}

	@Keyword
	public static void waitforImage(String image, double time) {
		try {
			_screen.wait(image, time);
			KeywordUtil.markPassed("Waited for the " + "'" + image + "'" +" Image for "+ time + "  Seconds and " + "wait method Passed");
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("waitforImage method Failed");
		}
	}


	@Keyword
	public static void setAutoWaitTimeout(double sec) {
		try {
			_screen.setAutoWaitTimeout(sec);
			KeywordUtil.markPassed("Waited for " + "'" + sec + "'"  + "  Seconds");
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("wait Failed");
		}
	}


	@Keyword
	public static void findAll(String img) {
		try {
			Iterator<Match> it = _screen.findAll(img);
			while (it.hasNext()) {
				it.next().highlight(1);
				KeywordUtil.markPassed("Findall method passed " + "'" + img + "'"  + "  Found");
			}
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("findAll Failed");
		}
	}


	@Keyword
	public static void findany(String img) {
		try {
			_screen.findAny(img);
			KeywordUtil.markPassed("Element " + img + " has been found successfully");
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to findany element");
		}
	}

	@Keyword
	public static void findAllCount(String img) {
		try {
			List<Match> it = _screen.findAllList(img);
			KeywordUtil.markPassed("Total Count of Image '" + img + "' = " + it.size());
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("findAllCount method Failed");
		}
	}

	@Keyword
	public static void scrollMouseWheelDown(String img, int script) {
		try {
			_screen.wheel(img, Button.WHEEL_DOWN, script);
			KeywordUtil.markPassed("Mouse has been scrolled down " + script + " times successfully");
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("scrollMouseWheelDown method failed");
		}
	}

	@Keyword
	public static void scrollMouseWheelUp(String img, int script) {
		try {
			_screen.wheel(img, Button.WHEEL_UP, script);
			KeywordUtil.markPassed("Mouse has been scrolled up " + script + " times successfully");
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("scrollMouseWheelUp method failed");
		}
	}


	@Keyword
	public static void clickSimilarImage(Pattern img) {

		try {
			_screen.wait(img.similar((float) 0.90), 2).click(); // Click the Image with 90% Match (0.90)
			KeywordUtil.markPassed(img + " matched and clicked successfully");
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("findAll Failed");
		}

		//		 Usage in Java Program:
		//		 final Pattern searchbar = new Pattern("imgs/search.png");
		//		 Sikuli.clickSimilarImage(searchbar);

	}

	@Keyword
	public static void openExeFile(String exeFilePath) {

		try {
			App calc = new App(exeFilePath);
			// App app = new App("C:\\Program Files\\Internet Explorer\\iexplore.exe");
			calc.open();
			Thread.sleep(2000);
			_screen.highlight(0);
			calc.close();
			KeywordUtil.markPassed("Application has been opened successfully");
		} catch (FindFailed e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Usage: 		Sikuli.openExeFile("C:\\Windows\\System32\\calc.exe");
	}


	/**
	 * Closes the current focused program.
	 */
	@Keyword
	public static void closeFocusedProgram() {
		try {
			if (System.getProperty("os.name").equals("Mac OS X")) {
				_screen.type("q", Key.CMD);
			}
			if (System.getProperty("os.name").contains("Windows")) {
				_screen.type(Key.F4, KeyModifier.WIN | KeyModifier.ALT);
			}
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}