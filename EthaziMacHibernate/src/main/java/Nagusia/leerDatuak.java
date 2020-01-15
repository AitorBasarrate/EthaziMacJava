package Nagusia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Stack;

import javax.swing.text.html.HTML.Tag;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class leerDatuak {

	int id_Ostatu = 0;
	
	public leerDatuak() {

	}

	public void leerAlojamientoRurales() {

		try {

			File fileAloRural = new File("../Hibernate/src/main/resources/alojamientoRural.xml");
			FileReader fr = new FileReader(fileAloRural);
			BufferedReader br = new BufferedReader(fr);
			String entrada;
			String cadena = "";

			while ((entrada = br.readLine()) != null) {
				cadena = cadena + entrada;
			}

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource archivo = new InputSource();
			archivo.setCharacterStream(new StringReader(cadena));

			Document documento = db.parse(archivo);
			documento.getDocumentElement().normalize();

			NodeList nodeLista = documento.getElementsByTagName("row");
//			System.out.println("Informacion de los libros");

			
			for (int s = 0; s < nodeLista.getLength(); s++) {

				Stack<Tag> tagStack = new Stack<Tag>();
				Node nodoResponsable = nodeLista.item(s);
				NodeList listaDeNodos;
				Element elementos;
				int cont = 0;
				String titulo = null;
				String descripcion = null;
				String telefono = null;
				String direccion = null;
				String marks = null; // zona ej:Costa vasca...
				String disf_fis = null;
				String disf_vis = null;
				String disf_audi = null;
				String disf_inte = null;
				String disf_orga = null;
				String email = null;
				String latitud = null;
				String longitud = null;

				if (nodoResponsable.getNodeType() == Node.ELEMENT_NODE) {

					System.out.println("-------------------------");
					Element primerElemento = (Element) nodoResponsable;
					listaDeNodos = primerElemento.getElementsByTagName("documentname");

					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						titulo = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						titulo = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("turismdescription");
					elementos = (Element) listaDeNodos.item(1);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						descripcion = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						descripcion = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("phone");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						telefono = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						telefono = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("address");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						direccion = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						direccion = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("marks");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						marks = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						marks = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("physical");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_fis = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("visual");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos.getFirstChild() != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_vis = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("auditive");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_audi = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("organic");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_orga = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("tourismemail");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						email = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else{
						email = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("latwgs84");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						latitud = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						latitud = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("lonwgs84");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						longitud = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						longitud = " ";
					}

				}

				Ostatu ostatu = new Ostatu(titulo, descripcion, marks, direccion, telefono, email, latitud, longitud);
				App app = new App();
				app.setup();
				app.create(ostatu);
				app.exit();

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void leerCampings() {

		try {

			File fileAloRural = new File("../Hibernate/src/main/resources/camping.xml");
			FileReader fr = new FileReader(fileAloRural);
			BufferedReader br = new BufferedReader(fr);
			String entrada;
			String cadena = "";

			while ((entrada = br.readLine()) != null) {
				cadena = cadena + entrada;
			}

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource archivo = new InputSource();
			archivo.setCharacterStream(new StringReader(cadena));

			Document documento = db.parse(archivo);
			documento.getDocumentElement().normalize();

			NodeList nodeLista = documento.getElementsByTagName("row");
//			System.out.println("Informacion de los libros");

			for (int s = 0; s < nodeLista.getLength(); s++) {

				Stack<Tag> tagStack = new Stack<Tag>();
				Node nodoResponsable = nodeLista.item(s);
				NodeList listaDeNodos;
				Element elementos;
				int cont = 0;
				String titulo = null;
				String descripcion = null;
				String telefono = null;
				String direccion = null;
				String marks = null; // zona ej:Costa vasca...
				String disf_fis = null;
				String disf_vis = null;
				String disf_audi = null;
				String disf_inte = null;
				String disf_orga = null;
				String email = null;
				String latitud = null;
				String longitud = null;

				if (nodoResponsable.getNodeType() == Node.ELEMENT_NODE) {

					System.out.println("-------------------------");
					Element primerElemento = (Element) nodoResponsable;
					listaDeNodos = primerElemento.getElementsByTagName("documentname");

					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						titulo = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						titulo = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("turismdescription");
					elementos = (Element) listaDeNodos.item(1);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						descripcion = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						descripcion = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("phone");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						telefono = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						telefono = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("address");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						direccion = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						direccion = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("marks");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						marks = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						marks = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("physical");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_fis = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("visual");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos.getFirstChild() != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_vis = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("auditive");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_audi = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("organic");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_orga = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("tourismemail");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						email = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else{
						email = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("latwgs84");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						latitud = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						latitud = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("lonwgs84");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						longitud = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						longitud = " ";
					}

				}

				Ostatu ostatu = new Ostatu(titulo, descripcion, marks, direccion, telefono, email, latitud, longitud);
				App app = new App();
				app.setup();
				app.create(ostatu);
				app.exit();

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void leerAlbergues() {

		try {

			File fileAloRural = new File("../Hibernate/src/main/resources/alberges.xml");
			FileReader fr = new FileReader(fileAloRural);
			BufferedReader br = new BufferedReader(fr);
			String entrada;
			String cadena = "";

			while ((entrada = br.readLine()) != null) {
				cadena = cadena + entrada;
			}

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource archivo = new InputSource();
			archivo.setCharacterStream(new StringReader(cadena));

			Document documento = db.parse(archivo);
			documento.getDocumentElement().normalize();

			NodeList nodeLista = documento.getElementsByTagName("row");
//			System.out.println("Informacion de los libros");

			for (int s = 0; s < nodeLista.getLength(); s++) {

				Stack<Tag> tagStack = new Stack<Tag>();
				Node nodoResponsable = nodeLista.item(s);
				NodeList listaDeNodos;
				Element elementos;
				int cont = 0;
				String titulo = null;
				String descripcion = null;
				String telefono = null;
				String direccion = null;
				String marks = null; // zona ej:Costa vasca...
				String disf_fis = null;
				String disf_vis = null;
				String disf_audi = null;
				String disf_inte = null;
				String disf_orga = null;
				String email = null;
				String latitud = null;
				String longitud = null;

				if (nodoResponsable.getNodeType() == Node.ELEMENT_NODE) {

					System.out.println("-------------------------");
					Element primerElemento = (Element) nodoResponsable;
					listaDeNodos = primerElemento.getElementsByTagName("documentname");

					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						titulo = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						titulo = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("turismdescription");
					elementos = (Element) listaDeNodos.item(1);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						descripcion = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						descripcion = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("phone");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						telefono = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						telefono = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("address");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						direccion = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						direccion = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("marks");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						marks = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						marks = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("physical");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_fis = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("visual");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos.getFirstChild() != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_vis = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("auditive");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_audi = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("organic");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						disf_orga = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}

					listaDeNodos = primerElemento.getElementsByTagName("tourismemail");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						email = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else{
						email = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("latwgs84");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						latitud = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						latitud = " ";
					}

					listaDeNodos = primerElemento.getElementsByTagName("lonwgs84");
					elementos = (Element) listaDeNodos.item(0);
					if (elementos != null) {
						listaDeNodos = elementos.getChildNodes();
						longitud = ((Node) listaDeNodos.item(0)).getNodeValue().toString();
					}else {
						longitud = " ";
					}

				}

				Ostatu ostatu = new Ostatu(titulo, descripcion, marks, direccion, telefono, email, latitud, longitud);
				App app = new App();
				app.setup();
				app.create(ostatu);
				app.exit();

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}