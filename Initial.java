package note;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileLockInterruptionException;
import java.util.Calendar;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class Initial extends JFrame implements ActionListener {

	private JPanel contentPane;
	FileDialog fd3;
	static Initial frame;
	File fout;
	FileOutputStream outsave;
    JTextArea area1;
    JScrollPane pane;
    JMenu mnFile,mnHelp,mnDate;
    JMenuBar menuBar;
    JMenuItem mntmOpen3,mntmOpen1,mntmOpen2,mntmOpen4;
    JFileChooser chooser;
	/**Launch the application.*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Initial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/** * Create the frame.
	 */
	public Initial(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Toolkit class for screen size
		setSize(823,495);
		setLayout(null);
		// text area -1
		//calender
		Calendar c=Calendar.getInstance();
		String dd,mm,yy;
		dd=String.valueOf(c.get(Calendar.DATE));
		mm=String.valueOf(c.get(Calendar.MONTH));
		yy=String.valueOf(c.get(Calendar.YEAR));
		chooser=new JFileChooser();
		area1=new JTextArea();
		area1.setBounds(50,50, 350,300);
		getContentPane().add(area1);
		pane=new JScrollPane(area1);
		
		// MENU----BAR
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.cyan);
		menuBar.setBounds(5, 11, 350, 25);
		add(menuBar);
		// MENUS
		mnHelp = new JMenu("help");
		mnFile = new JMenu("File");
		mnDate=new JMenu(dd+"/"+mm+1+"/"+yy);
		menuBar.add(mnFile);
		menuBar.add(mnHelp);
		menuBar.add(mnDate);
		// MENU ITEMS
		mntmOpen1 = new JMenuItem("New");
		mnFile.add(mntmOpen1);
		mntmOpen2 = new JMenuItem("Open");
		mnFile.add(mntmOpen2);
		mntmOpen3 = new JMenuItem("Save");
		mnFile.add(mntmOpen3);
		mntmOpen4 = new JMenuItem("Help");
		mnHelp.add(mntmOpen4);
		// listeners
		mntmOpen1.addActionListener(this);
		mntmOpen2.addActionListener(this);
		mntmOpen3.addActionListener(this);
		mntmOpen4.addActionListener(this);
		
	}
	// ---------ACTION----LISTENER
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()==mntmOpen4){
			Help help=new Help();  // help
		}
		else if(e.getSource()==mntmOpen3){   //save
			// if area empy error messsage before save
			if(area1.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "File is Empty!!");
			}
			else{
				//save algo
				try
				{
					// FileDialog--dubba--frame--title--
					fd3=new FileDialog(frame,"Open",FileDialog.SAVE);
					fd3.setVisible(true);  // setvisible
					//------------getselected file           
					fout=new File(fd3.getFile());
					frame.setTitle(fd3.getFile());
					if(!fout.exists())
					{
						//--if file doesn't exist create one 
						fout.createNewFile();
					}
					//------------------------// directory--add file
					outsave=new FileOutputStream(fd3.getDirectory()+fout);
					// write to file
					outsave.write(area1.getText().getBytes());
				}
				catch(Exception e2)
				{
					e2.printStackTrace();
				}
				String vsave=area1.getText();
			}//end saving if
		}// end elsif of save button
		else if(e.getSource()==mntmOpen2){
				int result=chooser.showOpenDialog(null);
				File file=chooser.getSelectedFile();
				if(result==JFileChooser.APPROVE_OPTION){
					String file_name=file.getPath();  // file-Path  to filename
					FileInputStream fr;
					try {
						fr = new FileInputStream(file_name);   // reading from file_name
					//	BufferedReader br=new BufferedReader(fr);  // helps reading fast
						String str="";
						int ch;
						try{
							while((ch=fr.read())!=-1){   //lines read
								area1.append(((char)ch)+"");
							}
							fr.close();  // close file reader
						}
						catch(Exception ee){ee.printStackTrace();}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					  //load file
			    }
		}
		else if(e.getSource()==mntmOpen1){
			// if area not empty show save message
			if(area1.getText().length()!=0){
				String[] choices = {"Yes, Save", "DISCARD", "CANCEL"};
			    int result = JOptionPane.showOptionDialog(this,"You have unsaved changes. Save before quitting?", "Warning",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, choices, choices[0]); 
			    //result 0 if save && 1 for discard ---------
			    if(result==0){
			    	// for save algo
						try
						{
							// FileDialog--dubba--frame--title--
							fd3=new FileDialog(frame,"Open",FileDialog.SAVE);
							fd3.setVisible(true);  // setvisible
							//------------getselected file           
							fout=new File(fd3.getFile());
							frame.setTitle(fd3.getFile());
							if(!fout.exists())
							{
								//--if file doesn't exist create one 
								fout.createNewFile();
							}
							//------------------------// directory--add file
							outsave=new FileOutputStream(fd3.getDirectory()+fout);
							// write to file
							outsave.write(area1.getText().getBytes());
						}
						catch(Exception e2)
						{
							e2.printStackTrace();
						}
						String vsave=area1.getText();
				}
			    else{
			    	area1.setText(null);
			    }// result if
			}// end area1.getText if 
			
			else{
				// empty area
				JOptionPane.showMessageDialog(null, "File is Empty!!");
			}
		}
	}// end of action
}
