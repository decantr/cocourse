/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocourse.server.gui;

import cocourse.server.Server;

import javax.swing.*;


/**
 * @author decanter
 */
public class NewServerGUI extends javax.swing.JDialog {

	private Server s;

	/**
	 * Creates new form diaNew
	 */
	public NewServerGUI( java.awt.Frame parent , boolean modal , Server s ) {
		super( parent , modal );
		this.s = s;
		initComponents( );
	}

	public Server getServer( ) {
		return this.s;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents( ) {

		jPanel1 = new javax.swing.JPanel( );
		lblGreeter = new javax.swing.JLabel( );
		btnClose = new javax.swing.JButton( );
		btnNew = new javax.swing.JButton( );
		txtPort = new javax.swing.JTextField( );
		lblGreeter1 = new javax.swing.JLabel( );

		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );

		lblGreeter.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );
		lblGreeter.setToolTipText( "" );

		btnClose.setText( "close" );
		btnClose.addActionListener( new java.awt.event.ActionListener( ) {
			public void actionPerformed( java.awt.event.ActionEvent evt ) {
				btnCloseActionPerformed( evt );
			}
		} );

		btnNew.setText( "new" );
		btnNew.addActionListener( new java.awt.event.ActionListener( ) {
			public void actionPerformed( java.awt.event.ActionEvent evt ) {
				btnNewActionPerformed( evt );
			}
		} );

		txtPort.setHorizontalAlignment( javax.swing.JTextField.CENTER );
		txtPort.setBorder( javax.swing.BorderFactory.createEmptyBorder( 1 , 1 , 1 , 1 ) );

		lblGreeter1.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );
		lblGreeter1.setText( "specify port or leave blank for auto" );
		lblGreeter1.setToolTipText( "" );

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout( jPanel1 );
		jPanel1.setLayout( jPanel1Layout );
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addComponent( lblGreeter , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
						.addGroup( javax.swing.GroupLayout.Alignment.TRAILING , jPanel1Layout.createSequentialGroup( )
								.addContainerGap( javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
								.addComponent( btnClose )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
								.addComponent( txtPort , javax.swing.GroupLayout.PREFERRED_SIZE , 80 , javax.swing.GroupLayout.PREFERRED_SIZE )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
								.addComponent( btnNew )
								.addGap( 30 , 30 , 30 ) )
						.addComponent( lblGreeter1 , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( jPanel1Layout.createSequentialGroup( )
								.addContainerGap( )
								.addComponent( lblGreeter1 )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED , 37 , Short.MAX_VALUE )
								.addComponent( lblGreeter )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED , 13 , Short.MAX_VALUE )
								.addGroup( jPanel1Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING , false )
										.addComponent( btnClose , javax.swing.GroupLayout.Alignment.TRAILING , javax.swing.GroupLayout.PREFERRED_SIZE , 24 , javax.swing.GroupLayout.PREFERRED_SIZE )
										.addComponent( btnNew )
										.addComponent( txtPort ) )
								.addContainerGap( ) )
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane( ) );
		getContentPane( ).setLayout( layout );
		layout.setHorizontalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( layout.createSequentialGroup( )
								.addContainerGap( )
								.addComponent( jPanel1 , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
								.addContainerGap( ) )
		);
		layout.setVerticalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( layout.createSequentialGroup( )
								.addContainerGap( )
								.addComponent( jPanel1 , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
								.addContainerGap( ) )
		);

		pack( );
	}// </editor-fold>//GEN-END:initComponents

	private void btnCloseActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btnCloseActionPerformed
		// TODO add your handling code here:
		// Show a confirmation dialog and test that dialog's output
		if ( JOptionPane
				.showConfirmDialog( null , "Are you sure you want to quit?" , "Confirm" , JOptionPane.YES_NO_OPTION )
				== JOptionPane.YES_OPTION ) {
			// Completely close the program
			System.exit( 0 );
		}
	}//GEN-LAST:event_btnCloseActionPerformed

	private void btnNewActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btnNewActionPerformed
		int p = 0;
		boolean b = true;

		if ( !txtPort.getText( ).equals( "" ) ) {
			p = Integer.parseInt( txtPort.getText( ) );
			b = Server.portFree( p );
		}

		if ( b ) {
			s = new Server( p );
			this.setVisible( false );
		} else fillGreeter( );

	}//GEN-LAST:event_btnNewActionPerformed

	void fillGreeter( ) {
		lblGreeter.setText( "There is already a server running" );
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnClose;
	private javax.swing.JButton btnNew;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JLabel lblGreeter;
	private javax.swing.JLabel lblGreeter1;
	private javax.swing.JTextField txtPort;
	// End of variables declaration//GEN-END:variables
}
