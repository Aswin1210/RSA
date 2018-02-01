/* Author : AswinKumar Manickam
 EECE-6580
Date:04/26/17
*/

import java.awt.image.BufferedImage; //This interface describes single-input/single-output operations performed on BufferedImage objects
import java.io.*;					 //imports all the classes that are defined in java.io package to this file. This enables java program to use those classes and their methods to achieve the task.
import java.net.*;					 //Provides the classes for implementing networking applications
import java.util.Random;
import java.util.Scanner;			 //The java.util.Scanner class is a simple text scanner which can parse primitive types and strings using regular expressions.Following are the important points about Scanner: A Scanner breaks its input into tokens using a delimiter pattern, which by default matches whitespace.
import java.math.*;

import javax.imageio.ImageIO;

public class Client {

	static BigInteger p, q, n, e, d, T, mess;//These are the variables for the encryption

	static BigInteger nD, dD, messD, dMes;//These are the variable for the decryption
	static int len = 1024;

	static byte[] RSA_en(byte[] message) {// RSA key generation and encryption
		
		byte tempRes[]= new byte[768]; // message(256)+n(256)+ d(256)
	        Random R=new Random();//For Key Generation.
		

	       
			p = new BigInteger(
				"100705698706356725832125633981178447511189012933008827908293580288203821613762683649128380987118478543956539245719638363498235397325144922957313946018345186727357707305243981690111616080878491709874480936963558022422755845372785136419991349503291371918995470229638562574414161271980424570270503802420834382693");  //Random prime number value is chosen for p, p=BigInteger.probablePrime(len,R)  
	
		q = new BigInteger(
				"148761542777795489870456904297046318838014566695238863866209454294363228732136511022486837628695342792543494915794684471075337980258433072246830472454903188010700906506481780979984750930468309586498376756490990040872232889964099304729290529700818862852186287832596860782455844816083252904435790579489409455649");  //Random prime number value is chosen for q, q=BigInteger.probablePrime(len,R)

		
		e = new BigInteger(
				"12652604729380209029747565797335916011689725878865771937862336854685648337926826365446638178344722550931417527336888606604951614951006272476369610354067401");  // e is chosen that has no common factors and relatively prime, e=BigInteger.probablePrime(512,R)
		
		d = new BigInteger(
				"9462315982437999676403150276233289566285412491149156330291945325321994648049854700682028374070316352802008057206859639364753139853403176847625432782075043762318993912718907973789765880776650277543856943950210003141269137603788926336621917102083247674286299016352874508831619857837464995712381859464890086991537701035245914897155884718202612767881660468768333071291704623682442807836000918101565915140440941570271865287694658071965437044610452463410255088721203825880831788795048837028648163176585466592203876730704279403269159186078332899821107136402685418442931405798449181877544017229486314571215169842311590868345");  //d is the multiplicative inverse, d=e.modInverse(T)
		n=p.multiply(q); // n= p*q
		mess = (new BigInteger(message)).modPow(e, n);
                //RSA Encryption

		System.arraycopy(mess.toByteArray(),0, tempRes,0,256); //object source will be mess.toByteArray(), int srcPos will be 0, object destination will be tempRes, int destPos will be 0, int length will be 256
		System.arraycopy(n.toByteArray(),0,tempRes,256,256);   //object source will be n.toByteArray(), int srcPos will be 0, object destination will be tempRes, int destPos will be 256, int length will be 256
		System.arraycopy(d.toByteArray(),0,tempRes,512,256);   //object source will be d.toByteArray(), int srcPos will be 0, object destination will be tempRes, int destPos will be 512, int length will be 256
		
		System.out.println("Decrypted mess "+ new String(mess.modPow(d, n).toByteArray()));
	
		return tempRes;
             }

	static byte[] RSA_Dcr(byte[] message) {
                // RSA Decryption

		byte dTemp[]= new byte[256];
		byte nTemp[]= new byte[256];
		byte mTemp[]= new byte[256];
		
		System.arraycopy(message,0,mTemp,0,256);        //object source will be message, int srcPos will be 0, object destination will be mtemp, int destPos will be 0, int length will be 256
		System.arraycopy(message, 256,nTemp,0,256);		//object source will be message, int srcPos will be 256, object destination will be ntemp, int destPos will be 0, int length will be 256
		System.arraycopy(message, 512,dTemp,0,256);		//object source will be message, int srcPos will be 512, object destination will be dtemp, int destPos will be 0, int length will be 256
		
		dD =new BigInteger(dTemp); 
		nD = new BigInteger(nTemp);
		messD = new BigInteger(mTemp);
		
		
		dMes = messD.modPow(dD, nD);// Decryption

		System.out.println("Decrypted Message " + new String(dMes.toByteArray()));
		return dMes.toByteArray();

	}

	private static final boolean DEBUG = true;

	public static void main(String[] args) {
		if (DEBUG) {
			System.out.println("Bob");
		}
		try {
			while(true){
			byte sendData[] = new byte[768];  //message(256) +n (256)+ d(256)
			DatagramSocket clientSocket = new DatagramSocket();
			
			InetAddress IpAddress = InetAddress.getByName("192.168.56.1");

			Scanner inOption = new Scanner(System.in);//
			System.out.println("Please enter text");

			String readData = inOption.nextLine();// reading the user data.
		

			DatagramPacket sendPacket;
			sendData =RSA_en(readData.getBytes());
			//System.out.println("Encrypted data :" + new String(sendData));

			sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, 1556);
			if (DEBUG) {
				//System.out.println("Sending data to server...");
			}
			clientSocket.send(sendPacket);
			byte[] receiveData = new byte[768];

			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			if (DEBUG) {
				//System.out.println("Waiting to receive data from server...");
			}
			clientSocket.receive(receivePacket);// receiving packets

			System.out.println("From Alice : " + new String(RSA_Dcr(receivePacket.getData())));
			clientSocket.close();
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
