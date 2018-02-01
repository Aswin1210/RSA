Author: ASWIN KUMAR MANICKAM

Name of the Files Submitted:
1. Server.java
2. Client.java
3. Project 2 Report.docx
4. Project 02 Readme.txt

Steps required to setup and run the program:
In windows:
a. open Command Prompt-> [set path where java files are. example->set path="C:\Program Files\Java\jdk1.8.0_121\bin"] Note:Should open 2 command Prompts, one for server and other for client.
b. Change Directory where the file is located. example-> cd Desktop
c. Run Javac Server.java, then if no error type "java Server".
d. Run Javac Client.java, then if no error type "java Client".
In simulation softwares:
1. Run Server.java file, output will show (Alice waiting for text).
2. Run Client.java file, output will show (Please enter the text).
3. The text entered in Client will be displayed in the Server.
4. Now server types, client receives the message from server and vice versa.Instant Messenger with RSA works.

Functions:
1. public static void arraycopy(Object drc, int srcPros, Object dest, int destPos, int length);
	The java.lang.System.arraycopy() method copies an array from the specified source array, beginning at the specified position, to the specified position of the destination array. A subsequence of array components are copied from the source array referenced by src to the destination array referenced by dest.The number of components copied is equal to the length argument. The components at positions srcPos through srcPos + length - 1 in the source array are copied into positions destPos through destPos + length - 1, respectively, of the destination array.
2. InetAddress IpAddress = InetAddress.getByName("129.63.204.102");
	InetAddress IpAddress = InetAddress.getByName("System Ip-address"); retuns the IP address for the given host name. To send bytes to a destination process, we shall need to obtain the address of the process. Part of this address is the IP address of the destination host. Throws UnknownHostExcemption if no IP address for the host could be found, or if a scope_id was specified for a global IPV6 address. 
3. String readData = inOption.nextLine();
	Gets the string which user enters.
4. DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
	The above line constructs the packet, send Packet, that the client will pop into the network through its socket. This packet includes that data that is contained in the packet, send Data, the length of this data, the IP address of the server, and the port number of the application. Note that send Packet is of type Datagram Packet.
5. clientSocket.receive(receivePacket);
	The client idles until it receives a packet; when it does receive a packet, it puts the packet in receivePacket.
6. clientSocket.close();
	This line closes the socket. Because UDP is connectionless, this line does not cause the client to send a tranport-layer message to the server (in contrast with TCPClient).

Packages:
1. import java.net.*; //Provides the classes for implementing networking applications
2. import java.util.Scanner; //The java.util.Scanner class is a simple text scanner which can parse primitive types and strings using regular expressions.Following are the important points about Scanner: A Scanner breaks its input into tokens using a delimiter pattern, which by default matches whitespace.
3. import java.awt.image.BufferedImage; //This interface describes single-input/single-output operations performed on BufferedImage objects