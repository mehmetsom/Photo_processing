import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
public class RGB_Analiz extends JFrame {






    private JTextField textFieldPath;



	private JLabel orjinalfoto;
    private JLabel rchanel;
	private JLabel gchanel;
    private JLabel bchanel;
    private JLabel gchanel2;
    private JLabel bchanel2;
    private JLabel rchanel2;



	private JButton browsebtn;

	public static final int WIDTH=100;



	public static final int HEIGHT=100;

   //Örnek
    // red_image = PIL.Image.open("red_image.png")
    //Create a PIL.Image object
    //
    //
    //red_image_rgb = red_image.convert("RGB")
    //Convert to RGB colorspace
    //
    //
    //rgb_pixel_value = red_image_rgb.getpixel((10,15))
    //Get color from (x, y) coordinates
    //
    //print(rgb_pixel_value)



	public RGB_Analiz() {

		setBounds(HEIGHT, HEIGHT, 660, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("RGB Analiz Uygulamasi");
		getContentPane().setLayout(null);
		orjinalfoto = new JLabel("");
		gchanel = new JLabel("");
		bchanel = new JLabel("");
		rchanel = new JLabel("");

		gchanel2 = new JLabel("Green");
		bchanel2 = new JLabel("Blue");
		rchanel2 = new JLabel("Red");
		browsebtn = new JButton("Browse");
		textFieldPath = new JTextField();
		textFieldPath.setColumns(10);

		textFieldPath.setBounds(130, 160, 280, 20);
		browsebtn.setBounds(420, 160, 90, 20);
		orjinalfoto.setBounds(210, 50, WIDTH, HEIGHT);
		rchanel.setBounds(10, 200, WIDTH, HEIGHT);
		gchanel.setBounds(230, 200, WIDTH, HEIGHT);
		bchanel.setBounds(450, 200, WIDTH, HEIGHT);
		rchanel2.setBounds(10, 180, 100, 20);
		gchanel2.setBounds(230, 180, 100, 20);
		bchanel2.setBounds(450, 180, 100, 20);

		getContentPane().add(browsebtn);
		getContentPane().add(rchanel);
		getContentPane().add(gchanel);
		getContentPane().add(bchanel);
		getContentPane().add(rchanel2);
		getContentPane().add(gchanel2);
		getContentPane().add(bchanel2);
		getContentPane().add(orjinalfoto);
		getContentPane().add(textFieldPath);

		browsebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] pixel;

				int rArray[] = new int[256];
				int gArray[] = new int[256];
				int bArray[] = new int[256];

				File secilenDosya,orjinalDosya;
				BufferedImage orjimg,redimg,blueimg,greenimg;

				JFileChooser fileChooser = new JFileChooser("C:");

				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					if(!fileChooser.getSelectedFile().getAbsolutePath().toLowerCase().endsWith(".bmp")){
						JOptionPane.showMessageDialog(null,"BMP uzantili fotograf secmelisiniz.");
						return;
					}

					secilenDosya = fileChooser.getSelectedFile();

					orjinalDosya = new File("C:\\Original.bmp");
					try {

						textFieldPath.setText(secilenDosya.getAbsolutePath());
						orjimg = ImageIO.read(secilenDosya);
						Image resize = orjimg.getScaledInstance(WIDTH,HEIGHT,Image.SCALE_SMOOTH);
						ImageIO.write(orjimg, "bmp", orjinalDosya);
						ImageIcon icon = new ImageIcon(resize);
						orjinalfoto.setIcon(icon);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,"Okuma hatasi.");
					}
					try {

						orjimg = ImageIO.read(secilenDosya);
						int yukseklik = orjimg.getHeight();
						int genislik = orjimg.getWidth();

						for (int y = 0; y < orjimg.getHeight(); y++) {
							for (int x = 0; x < orjimg.getWidth(); x++) {

								pixel = orjimg.getRaster().getPixel(x, y, new int[3]); // 155,55,40
								rArray[pixel[0]]++;
								gArray[pixel[1]]++;
								bArray[pixel[2]]++;
							}
						}

						FileWriter fileWriter = new FileWriter("C:\\RGB.txt");
						fileWriter.write("R kanalinda\t\tG kanalinda\t\tB kanalinda\n");
						for(int i=0;i<255;i++) {
							fileWriter.write((i+1)+" degeri - "+rArray[i+1]+" adet\t" + (i+1)+" degeri - "+gArray[i+1]+" adet\t"+(i+1)+" degeri - "+bArray[i+1]+" adet.\n");
						}
						fileWriter.close();
						 redimg =  ImageIO.read(secilenDosya);
						 greenimg = ImageIO.read(secilenDosya);
						 blueimg =  ImageIO.read(secilenDosya);

						 int p,a,r,g,b,ort;
						for (int y = 0; y < yukseklik; y++)
						{
							for (int x = 0; x < genislik; x++)
							{

								 p = redimg.getRGB(x,y);
								 a = (p>>24)&0xff;
								 r = (p>>16)&0xff;
								 g = (0>>8)&0xff;
								 b = 0&0xff;
								 ort = (r+g+b)/3;
								p = (a<<24) | (ort<<16) | (ort<<8) | ort;
								redimg.setRGB(x, y, p);

								p = greenimg.getRGB(x,y);
								a = (p>>24)&0xff;
								r = (0>>16)&0xff;
								g = (p>>8)&0xff;
								b = 0&0xff;
								ort = (r+g+b)/3;
								p = (a<<24) | (ort<<16) | (ort<<8) | ort;
								greenimg.setRGB(x, y, p);

								p = blueimg.getRGB(x,y);
								a = (p>>24)&0xff;
								r = (0>>16)&0xff;
								g = (0>>8)&0xff;
								b = p&0xff;
								ort = (r+g+b)/3;
								p = (a<<24) | (ort<<16) | (ort<<8) | ort;
								blueimg.setRGB(x, y, p);
							}
						}

						gchanel.setIcon(ResimKaydet(greenimg,"C:\\Green.bmp"));
						rchanel.setIcon(ResimKaydet(greenimg,"C:\\Red.bmp"));
						bchanel.setIcon(ResimKaydet(greenimg,"C:\\Blue.bmp"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Degerler kaydedildi");
				}
			}
		});

	}
	public static void main(String[] args) {
		try {
			RGB_Analiz frame = new RGB_Analiz();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


    ImageIcon ResimKaydet(BufferedImage resim, String yol){
        try {

            File tempDosya = new File(yol);
            ImageIO.write(resim, "bmp", tempDosya);
            Image resize = resim.getScaledInstance(WIDTH,HEIGHT,Image.SCALE_SMOOTH);
            return new ImageIcon(resize);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }}