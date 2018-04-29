/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocourse;

import cocourse.server.gui.serverfront;
import cocourse.server.serverback;

/**
 * @author decanter
 */
public class Cocourse {

	/**
	 * @param args the command line arguments
	 */
	public static void main( String[] args ) {
		// TODO code application logic here

		// for testing


//		if ( args.length > 0 )
			serverback s = new serverback(Integer.parseInt(
//					args[1]
			"" + 8182
			));

			s.start();

	}

}
