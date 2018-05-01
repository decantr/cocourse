/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocourse.client.gui;

import cocourse.client.Client;

import javax.swing.*;
import java.text.SimpleDateFormat;

/**
 * @author decanter
 */
public class ClientGUI extends javax.swing.JFrame {

	private Client client;
	private Timer timer;
	private boolean end = false;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JMenuItem btnOpenLog;
	private javax.swing.JButton btnPlaceBid;
	private javax.swing.JDialog diaLog;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JLabel lblCurrentTime;
	private javax.swing.JLabel lblItemName;
	private javax.swing.JLabel lblTime;
	private javax.swing.JLabel lblTimeLeft;
	private javax.swing.JTextArea txtDesc;
	private javax.swing.JTextField txtHighBid;
	private javax.swing.JTextField txtHighBidder;
	/**
	 * Creates new form clientfront
	 */
	public ClientGUI( ) {
		ConnectGUI c = new ConnectGUI( this , true , client );
		c.setVisible( true );
		client = c.getClient( );

		if ( client == null ) System.exit( 0 );

		try {
			Thread.sleep( 1000L );
		} catch ( InterruptedException e ) {
			e.printStackTrace( );
		}

		timer = new javax.swing.Timer( 100 , e -> {
			this.lblCurrentTime.setText(
					new SimpleDateFormat( "hh:mm:ss" ).format( System.currentTimeMillis( ) ) );

			boolean r = false;

			if ( this.client.getAuction( ) != null)
				r = true;

			this.lblItemName.setText( r ? this.client.getAuction( ).getUser( ) : "No Auction" );
			this.txtDesc.setText( r ? this.client.getAuction( ).getDesc( ) : "" );

			if ( r && this.client.getAuction( ).isRunning( ) ) {
				end = false;
				r = true;
				System.out.println( System.currentTimeMillis() + " " + this.client.getAuction( ).timeLeft( ) );
				System.out.println( new SimpleDateFormat( "mm:ss" ).format(System.currentTimeMillis() )
						+ " " + new SimpleDateFormat( "mm:ss" ).format(this.client.getAuction( ).timeLeft( ) ));
				System.out.println( client.getAuction().timeLeft() );
			} else r = false;


			this.lblTimeLeft.setText( r ?
					new SimpleDateFormat( "mm:ss" ).format( this.client.getAuction( ).timeLeft( ) ) : new SimpleDateFormat( "mm:ss" ).format( 0L ) );
			this.txtHighBid.setText( r ? "" + this.client.getAuction( ).getBidHigh( ).getAmount( ) : "" );
			this.txtHighBidder.setText( r ? this.client.getAuction( ).getBidHigh( ).getUser( ) : "" );

			if ( this.client.getAuction( ) != null && this.client.getAuction( ).getEnded( ) && !end) {
				JOptionPane.showMessageDialog( this ,
						client.getAuction( ).getBidHigh( ).getUser( ).equals( this.client.getUser( ) ) ?
								"You won! Congratulations" : "You lost! " + client.getAuction( ).getBidHigh( ).getUser( ) + " won!"
				);
				end = true;
			}
		} );

		timer.start( );

		initComponents( );

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main( String args[] ) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels( ) ) {
				if ( "Nimbus".equals( info.getName( ) ) ) {
					javax.swing.UIManager.setLookAndFeel( info.getClassName( ) );
					break;
				}
			}
		} catch ( ClassNotFoundException ex ) {
			java.util.logging.Logger.getLogger( ClientGUI.class.getName( ) )
					.log( java.util.logging.Level.SEVERE , null , ex );
		} catch ( InstantiationException ex ) {
			java.util.logging.Logger.getLogger( ClientGUI.class.getName( ) )
					.log( java.util.logging.Level.SEVERE , null , ex );
		} catch ( IllegalAccessException ex ) {
			java.util.logging.Logger.getLogger( ClientGUI.class.getName( ) )
					.log( java.util.logging.Level.SEVERE , null , ex );
		} catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
			java.util.logging.Logger.getLogger( ClientGUI.class.getName( ) )
					.log( java.util.logging.Level.SEVERE , null , ex );
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater( new Runnable( ) {
			public void run( ) {
				new ClientGUI( ).setVisible( true );
			}
		} );
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents( ) {

		diaLog = new javax.swing.JDialog( );
		jScrollPane2 = new javax.swing.JScrollPane( );
		jTextArea1 = new javax.swing.JTextArea( );
		lblItemName = new javax.swing.JLabel( );
		jScrollPane1 = new javax.swing.JScrollPane( );
		txtDesc = new javax.swing.JTextArea( );
		lblTime = new javax.swing.JLabel( );
		jLabel1 = new javax.swing.JLabel( );
		txtHighBid = new javax.swing.JTextField( );
		jLabel2 = new javax.swing.JLabel( );
		txtHighBidder = new javax.swing.JTextField( );
		jSeparator1 = new javax.swing.JSeparator( );
		jLabel3 = new javax.swing.JLabel( );
		jTextField1 = new javax.swing.JTextField( );
		jLabel4 = new javax.swing.JLabel( );
		btnPlaceBid = new javax.swing.JButton( );
		lblTimeLeft = new javax.swing.JLabel( );
		lblCurrentTime = new javax.swing.JLabel( );
		jMenuBar1 = new javax.swing.JMenuBar( );
		jMenu1 = new javax.swing.JMenu( );
		jMenu2 = new javax.swing.JMenu( );
		btnOpenLog = new javax.swing.JMenuItem( );

		jScrollPane2.setHorizontalScrollBarPolicy( javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		jScrollPane2.setVerticalScrollBarPolicy( javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		jScrollPane2.setAutoscrolls( true );

		jTextArea1.setColumns( 20 );
		jTextArea1.setRows( 5 );
		jScrollPane2.setViewportView( jTextArea1 );

		javax.swing.GroupLayout diaLogLayout = new javax.swing.GroupLayout( diaLog.getContentPane( ) );
		diaLog.getContentPane( ).setLayout( diaLogLayout );
		diaLogLayout.setHorizontalGroup(
				diaLogLayout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( diaLogLayout.createSequentialGroup( )
								.addContainerGap( )
								.addComponent( jScrollPane2 , javax.swing.GroupLayout.DEFAULT_SIZE , 376 , Short.MAX_VALUE )
								.addContainerGap( ) )
		);
		diaLogLayout.setVerticalGroup(
				diaLogLayout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( diaLogLayout.createSequentialGroup( )
								.addContainerGap( )
								.addComponent( jScrollPane2 , javax.swing.GroupLayout.DEFAULT_SIZE , 276 , Short.MAX_VALUE )
								.addContainerGap( ) )
		);

		setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );

		lblItemName.setText( "itemname" );

		txtDesc.setEditable( false );
		txtDesc.setColumns( 20 );
		txtDesc.setRows( 5 );
		jScrollPane1.setViewportView( txtDesc );

		lblTime.setText( "timeleft" );

		jLabel1.setText( "current bid" );

		txtHighBid.setEditable( false );

		jLabel2.setText( "Bidder" );

		txtHighBidder.setEditable( false );

		jLabel3.setText( "Place Bid: " );

		jLabel4.setText( "£" );

		btnPlaceBid.setText( "Bid" );
		btnPlaceBid.addActionListener( new java.awt.event.ActionListener( ) {
			public void actionPerformed( java.awt.event.ActionEvent evt ) {
				btnPlaceBidActionPerformed( evt );
			}
		} );

		lblTimeLeft.setText( "00:00" );

		lblCurrentTime.setText( "00:00:00" );

		jMenu1.setText( "File" );
		jMenuBar1.add( jMenu1 );

		jMenu2.setText( "Log" );

		btnOpenLog.setText( "Open Log" );
		btnOpenLog.addActionListener( new java.awt.event.ActionListener( ) {
			public void actionPerformed( java.awt.event.ActionEvent evt ) {
				btnOpenLogActionPerformed( evt );
			}
		} );
		jMenu2.add( btnOpenLog );

		jMenuBar1.add( jMenu2 );

		setJMenuBar( jMenuBar1 );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane( ) );
		getContentPane( ).setLayout( layout );
		layout.setHorizontalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( layout.createSequentialGroup( )
								.addContainerGap( )
								.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
										.addGroup( layout.createSequentialGroup( )
												.addComponent( jScrollPane1 , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
												.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
												.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
														.addGroup( layout.createSequentialGroup( )
																.addComponent( jLabel2 )
																.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED , 37 , Short.MAX_VALUE )
																.addComponent( txtHighBidder , javax.swing.GroupLayout.PREFERRED_SIZE , 68 , javax.swing.GroupLayout.PREFERRED_SIZE ) )
														.addGroup( layout.createSequentialGroup( )
																.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																		.addGroup( layout.createSequentialGroup( )
																				.addGap( 0 , 0 , Short.MAX_VALUE )
																				.addComponent( jLabel1 )
																				.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ) )
																		.addGroup( layout.createSequentialGroup( )
																				.addComponent( lblTime , javax.swing.GroupLayout.PREFERRED_SIZE , 62 , javax.swing.GroupLayout.PREFERRED_SIZE )
																				.addGap( 12 , 12 , 12 ) ) )
																.addGroup( layout
																		.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING , false )
																		.addComponent( lblTimeLeft , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
																		.addComponent( txtHighBid , javax.swing.GroupLayout.DEFAULT_SIZE , 68 , Short.MAX_VALUE ) ) ) ) )
										.addComponent( jSeparator1 )
										.addGroup( layout.createSequentialGroup( )
												.addGap( 84 , 84 , 84 )
												.addComponent( jLabel3 )
												.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
												.addComponent( jLabel4 )
												.addGap( 4 , 4 , 4 )
												.addComponent( jTextField1 , javax.swing.GroupLayout.PREFERRED_SIZE , 50 , javax.swing.GroupLayout.PREFERRED_SIZE )
												.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
												.addComponent( btnPlaceBid )
												.addGap( 0 , 0 , Short.MAX_VALUE ) )
										.addGroup( layout.createSequentialGroup( )
												.addComponent( lblItemName )
												.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED , javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
												.addComponent( lblCurrentTime ) ) )
								.addContainerGap( ) )
		);
		layout.setVerticalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( layout.createSequentialGroup( )
								.addContainerGap( )
								.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
										.addComponent( lblItemName )
										.addComponent( lblCurrentTime , javax.swing.GroupLayout.Alignment.TRAILING ) )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
								.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
										.addGroup( layout.createSequentialGroup( )
												.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
														.addComponent( lblTime )
														.addComponent( lblTimeLeft ) )
												.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
												.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
														.addComponent( jLabel1 )
														.addComponent( txtHighBid , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
												.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
												.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
														.addComponent( jLabel2 )
														.addComponent( txtHighBidder , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) ) )
										.addComponent( jScrollPane1 , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
								.addComponent( jSeparator1 , javax.swing.GroupLayout.PREFERRED_SIZE , 10 , javax.swing.GroupLayout.PREFERRED_SIZE )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
								.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
										.addComponent( jLabel3 )
										.addComponent( jTextField1 , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
										.addComponent( jLabel4 )
										.addComponent( btnPlaceBid ) )
								.addContainerGap( javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE ) )
		);

		pack( );
	}// </editor-fold>//GEN-END:initComponents

	private void btnPlaceBidActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btnPlaceBidActionPerformed
		try {
			double d = Double.parseDouble( jTextField1.getText( ) );
			client.makeBid( d );
		} catch ( Exception ignored ) {
			JOptionPane.showMessageDialog( this , "Bad bid type" );
		}

	}//GEN-LAST:event_btnPlaceBidActionPerformed

	private void btnOpenLogActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btnOpenLogActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_btnOpenLogActionPerformed
	// End of variables declaration//GEN-END:variables
}
