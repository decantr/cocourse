/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocourse.server.gui;

import cocourse.Auction;

import javax.swing.*;

/**
 * @author decanter
 */
public class NewAuctionGUI extends javax.swing.JDialog {

	private Auction auction;

	/**
	 * Creates new form NewAuctionGUI
	 */
	public NewAuctionGUI( java.awt.Frame parent , boolean modal ) {
		super( parent , modal );
		initComponents( );
	}

	public Auction getAuction( ) {
		return this.auction;
	}


	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents( ) {

		jLabel1 = new javax.swing.JLabel( );
		btnCreate = new javax.swing.JButton( );
		txtName = new javax.swing.JTextField( );
		jLabel2 = new javax.swing.JLabel( );
		jScrollPane1 = new javax.swing.JScrollPane( );
		txtDesc = new javax.swing.JTextArea( );
		txtBid = new javax.swing.JTextField( );
		jLabel3 = new javax.swing.JLabel( );
		jLabel4 = new javax.swing.JLabel( );
		txtDuration = new javax.swing.JTextField( );
		jLabel5 = new javax.swing.JLabel( );
		jLabel6 = new javax.swing.JLabel( );

		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setTitle( "Create New Auction" );

		jLabel1.setText( "Name" );

		btnCreate.setText( "Create" );
		btnCreate.addActionListener( new java.awt.event.ActionListener( ) {
			public void actionPerformed( java.awt.event.ActionEvent evt ) {
				btnCreateActionPerformed( evt );
			}
		} );

		jLabel2.setText( "Description" );

		txtDesc.setColumns( 20 );
		txtDesc.setRows( 5 );
		jScrollPane1.setViewportView( txtDesc );

		txtBid.setText( "0.99" );

		jLabel3.setText( "Initial Bid" );

		jLabel4.setText( "Duration" );

		txtDuration.setText( "60" );

		jLabel5.setText( "£" );

		jLabel6.setText( "Seconds" );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane( ) );
		getContentPane( ).setLayout( layout );
		layout.setHorizontalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( layout.createSequentialGroup( )
								.addContainerGap( )
								.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
										.addGroup( javax.swing.GroupLayout.Alignment.TRAILING , layout.createSequentialGroup( )
												.addGap( 0 , 0 , Short.MAX_VALUE )
												.addComponent( btnCreate ) )
										.addGroup( layout.createSequentialGroup( )
												.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
														.addComponent( jLabel2 )
														.addComponent( jLabel3 )
														.addComponent( jLabel1 )
														.addComponent( jLabel4 ) )
												.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
												.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING , false )
														.addComponent( jScrollPane1 )
														.addComponent( txtName )
														.addGroup( layout.createSequentialGroup( )
																.addComponent( jLabel5 )
																.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
																.addComponent( txtBid ) )
														.addGroup( javax.swing.GroupLayout.Alignment.TRAILING , layout.createSequentialGroup( )
																.addComponent( txtDuration )
																.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
																.addComponent( jLabel6 ) ) )
												.addGap( 0 , 16 , Short.MAX_VALUE ) ) )
								.addContainerGap( ) )
		);
		layout.setVerticalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( layout.createSequentialGroup( )
								.addContainerGap( )
								.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
										.addComponent( jLabel1 )
										.addComponent( txtName , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
								.addGap( 18 , 18 , 18 )
								.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
										.addComponent( jLabel2 )
										.addComponent( jScrollPane1 , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
								.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
										.addComponent( txtBid , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
										.addComponent( jLabel3 )
										.addComponent( jLabel5 ) )
								.addGap( 18 , 18 , 18 )
								.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
										.addComponent( jLabel4 )
										.addComponent( txtDuration , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
										.addComponent( jLabel6 ) )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED , javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
								.addComponent( btnCreate )
								.addContainerGap( ) )
		);

		pack( );
	}// </editor-fold>//GEN-END:initComponents

	private void btnCreateActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btnCreateActionPerformed
		try {
			this.auction = new Auction(
					txtName.getText( ) , txtDesc.getText( ) ,
					Double.parseDouble( txtBid.getText( ) ) ,
					Long.parseLong( txtDuration.getText( ) )
			);
			this.setVisible( false );
		} catch ( NumberFormatException e ) {
			JOptionPane.showMessageDialog( this , "Fields invalid" );
		}
	}//GEN-LAST:event_btnCreateActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCreate;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField txtBid;
	private javax.swing.JTextArea txtDesc;
	private javax.swing.JTextField txtDuration;
	private javax.swing.JTextField txtName;
	// End of variables declaration//GEN-END:variables
}