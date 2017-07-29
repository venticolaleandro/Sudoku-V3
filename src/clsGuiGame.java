import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;



public class clsGuiGame extends JFrame implements ActionListener, KeyListener, Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JToggleButton[][] botones;
	private clsSudoku tablero;
	private final int sudokuAncho=510;
	private final int sudokuAlto=550;
	private JPanel panelSudoku;
	private JMenuBar BarraMenu;
	private JMenu menu;
	private JMenuItem nuevo;
	private JMenuItem salir;
	public static Thread thread;
	
	public clsGuiGame(){
		this.setTitle("..::Sudoku::..");
		this.setSize(this.sudokuAncho, this.sudokuAlto);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.BarraMenu= new JMenuBar();
		this.setJMenuBar(BarraMenu);
		this.menu= new JMenu("Juego");
		this.BarraMenu.add(menu);
		this.nuevo=new JMenuItem("Nuevo");
		this.salir=new JMenuItem("Salir");
		this.nuevo.addActionListener(this);
		this.salir.addActionListener(this);
		this.menu.add(nuevo);
		this.menu.add(salir);
		
		this.panelSudoku= new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.panelSudoku.setPreferredSize(new Dimension(this.sudokuAncho,this.sudokuAlto));
		
		this.tablero= new clsSudoku();
		
		botones= new JToggleButton[9][9];
		
		for (int fila = 0; fila < 9; fila++){
			for (int columna = 0; columna < 9; columna++) {
				botones[fila][columna]=new JToggleButton();
				botones[fila][columna].setBackground(this.getColor(fila, columna));
				botones[fila][columna].addActionListener(this);
				botones[fila][columna].addKeyListener(this);
				botones[fila][columna].setPreferredSize(new Dimension(50,50));
				this.add(botones[fila][columna]);
			}
		}
		this.add(panelSudoku);
		this.setVisible(true);
	}
	
	public clsSudoku getTablero(){
		return this.tablero;
	}
	
	public Color getColor(int fila, int columna){
		Color nuevo=new Color(0,0,0);
		if((fila>=0)&&(fila<=2)){
			if((columna>=0)&&(columna<=2)){
				nuevo=new Color(255,255,255);
			}
			if((columna>=3)&&(columna<=5)){
				nuevo=new Color(210,210,210);
			}
			if((columna>=6)&&(columna<=8)){
				nuevo=new Color(255,255,255);
			}
		}
		if((fila>=3)&&(fila<=5)){
			if((columna>=0)&&(columna<=2)){
				nuevo=new Color(210,210,210);
			}
			if((columna>=3)&&(columna<=5)){
				nuevo=new Color(255,255,255);
			}
			if((columna>=6)&&(columna<=8)){
				nuevo=new Color(210,210,210);
			}
		}
		if((fila>=6)&&(fila<=8)){
			if((columna>=0)&&(columna<=2)){
				nuevo=new Color(255,255,255);
			}
			if((columna>=3)&&(columna<=5)){
				nuevo=new Color(210,210,210);
			}
			if((columna>=6)&&(columna<=8)){
				nuevo=new Color(255,255,255);
			}
		}
		return nuevo;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1){
			setNumero(1);
		}else if(e.getKeyCode() == KeyEvent.VK_2){
			setNumero(2);
		}else if(e.getKeyCode() == KeyEvent.VK_3){
			setNumero(3);
		}else if(e.getKeyCode() == KeyEvent.VK_4){
			setNumero(4);
		}else if(e.getKeyCode() == KeyEvent.VK_5){
			setNumero(5);
		}else if(e.getKeyCode() == KeyEvent.VK_6){
			setNumero(6);
		}else if(e.getKeyCode() == KeyEvent.VK_7){
			setNumero(7);
		}else if(e.getKeyCode() == KeyEvent.VK_8){
			setNumero(8);
		}else if(e.getKeyCode() == KeyEvent.VK_9){
			setNumero(9);
		}else if(e.getKeyCode() == KeyEvent.VK_0){
			setNumero(0);
		}
	}
	
	private void setNumero(int number){
		for(int fila = 0; fila < 9; fila++){
			for(int columna = 0; columna <9 ; columna++){
				if(botones[fila][columna].isSelected()){
					this.setColorNormal(fila, columna);
					if(getTablero().getC(fila, columna).esDado()){
						botones [fila][columna].validate();	
						botones [fila][columna].setSelected(false);
						return;
					}
					botones [fila][columna].setText(Integer.toString(number));
					botones [fila][columna].validate();
					getTablero().getC(fila, columna).setValor(number);
					botones [fila][columna].setBackground(this.getColor(fila, columna));
					if(!getTablero().esFactible(fila, columna)){
						botones [fila][columna].setBackground(Color.red);
					}else{
						if(!(this.getTablero().hayVacios())){
							if(this.getTablero().checkTablero(0, 0)){
								JOptionPane.showMessageDialog(null, "Juego terminado - Felicidades!");
								this.dispose();
							}else{
								JOptionPane.showMessageDialog(null, "Revisa el tablero D:");
							}
						}
					}
					botones [fila][columna].setSelected(false);
					return;
				}
			}
		}
		
	}
	
	public void actualizarGui(){
		for(int fila = 0; fila < 9; fila++){
			for(int columna = 0; columna <9 ; columna++){				
				if(getTablero().getC(fila, columna).esDado()){
					botones [fila][columna].setText(Integer.toString(this.getTablero().getC(fila, columna).getValor()));
					botones [fila][columna].setForeground(Color.RED);
					botones [fila][columna].validate();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Nuevo")){
			this.getTablero().generarTablero(this.setDificultad());
			this.actualizarGui();
		}else if(e.getActionCommand().equals("Salir")){
			this.dispose();
		}
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(botones[i][j]==e.getSource()){
					if(this.getTablero().getC(i, j).esDado()){
						botones[i][j].setSelected(false);
					}else{
						this.setAzul(i, j);
					}
				}
			}
		}
	}
	
	public int setDificultad(){
		int seleccion=JOptionPane.showOptionDialog(
				this,
				"Seleccione una opcion",
				"Seleccione una dificultad",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				new Object[]{"Facil","Medio","Dificil"},
				"Facil");
		
		return seleccion;
	}
	
	public void setAzul(int fila, int columna){
		int Cfila = (fila / 3) * 3;
		int Ccolumna = (columna / 3) * 3;
		
		for(int f = 0; f<3; f++){
			for(int c = 0; c<3; c++){
				botones[Cfila+f][Ccolumna+c].setBackground(new Color(70,100,255));
			}
		}
		
		for(int i=0;i<9;i++){
			botones[fila][i].setBackground(new Color(140,255,230));
			botones[i][columna].setBackground(new Color(140,255,230));
		}
		
	}
	
	public void setColorNormal(int fila, int columna){
		
		int Cfila = (fila / 3) * 3;
		int Ccolumna = (columna / 3) * 3;
		
		for(int f = 0; f<3; f++){
			for(int c = 0; c<3; c++){
				botones[Cfila+f][Ccolumna+c].setBackground(this.getColor(Cfila+f, Ccolumna+c));
			}
		}
		for(int i=0;i<9;i++){
			botones[fila][i].setBackground(this.getColor(fila, i));
			botones[i][columna].setBackground(this.getColor(i, columna));
		}
	}
}