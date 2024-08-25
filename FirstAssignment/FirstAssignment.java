/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile Linux:	javac -cp .:mvAcm.jar FirstAssignment.java
 *	To execute Linux:	java -cp .:mvAcm.jar FirstAssignment
 *
 *	To compile MS Powershell:	javac -cp ".;mvAcm.jar" FirstAssignment.java
 *	To execute MS Powershell:	java -cp ".;mvAcm.jar" FirstAssignment
 *
 *	@author	Shrey Vishen
 *	@since	August 24, 2024
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
	 
	public void run() {
		 // The font to be used
		Font f = new Font("Serif", Font.BOLD, 18);
		
		// Line 1
		GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
		s1.setFont(f);
		add(s1);
	
		// Lines 2-16
		GLabel s2 = new GLabel("This summer, I visited national parks like Glacier and Yosemite, taking in their beauty.", 10, 40);
		s2.setFont(f);
		add(s2);

		GLabel s3 = new GLabel("Yosemite's granite cliffs and giant sequoias left a lasting impression on me.", 10, 60);
		s3.setFont(f);
		add(s3);
		
		GLabel s4 = new GLabel("Glacier's stunning lakes and towering mountains offered an escape into nature.", 10, 80);
		s4.setFont(f);
		add(s4);
		
		GLabel s5 = new GLabel("Exploring these parks was a refreshing way to connect with the natural world.", 10, 100);
		s5.setFont(f);
		add(s5);
		
		GLabel s6 = new GLabel("When I wasn't hiking, I played volleyball almost every day with friends.", 10, 120);
		s6.setFont(f);
		add(s6);
		
		GLabel s7 = new GLabel("These games were a great way to stay active and enjoy time with my friends.", 10, 140);
		s7.setFont(f);
		add(s7);
		
		GLabel s8 = new GLabel("The energy and fun we had together made each match a highlight of my summer.", 10, 160);
		s8.setFont(f);
		add(s8);
		
		GLabel s9 = new GLabel("On the academic side, I completed a course in AP Computer Science Principles.", 10, 180);
		s9.setFont(f);
		add(s9);

		GLabel s10 = new GLabel("I also studied for competitions and olympics for the school year, such as the AMC 12", 10, 200);
		s10.setFont(f);
		add(s10);
		
		GLabel s11 = new GLabel("To add, I studied Linear Algebra, which I found both challenging and rewarding.", 10, 220);
		s11.setFont(f);
		add(s11);
		
		GLabel s12 = new GLabel("In addition to these courses, I worked on research related to Neural Radiance Fields (NeRFs).", 10, 240);
		s12.setFont(f);
		add(s12);
		
		GLabel s13 = new GLabel("This research allowed me to dive deeper into cutting-edge technology.", 10, 260);
		s13.setFont(f);
		add(s13);
		
		GLabel s14 = new GLabel("I enjoyed the challenge of learning new concepts and applying them practically.", 10, 280);
		s14.setFont(f);
		add(s14);
		
		GLabel s15 = new GLabel("Overall, this summer was a great mix of outdoor adventures, sports, and learning.", 10, 300);
		s15.setFont(f);
		add(s15);
		
		GLabel s16 = new GLabel("It was a well-rounded experience that left me both refreshed and ready for what's next.", 10, 320);
		s16.setFont(f);
		add(s16);
	}
}
 
