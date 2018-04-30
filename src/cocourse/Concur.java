/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocourse;

import cocourse.server.Server;

/**
 * @author decanter
 */
public class Concur {

	/**
	 * @param args the command line arguments
	 */
	public static void main( String[] args ) {
		// TODO code application logic here

		// for testing


//		if ( args.length > 0 )
			Server s = new Server(Integer.parseInt(
//					args[1]
			"" + 8182
			));

			s.start();

	}

}
