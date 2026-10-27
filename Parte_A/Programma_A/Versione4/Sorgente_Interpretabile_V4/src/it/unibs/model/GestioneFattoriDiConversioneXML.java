package it.unibs.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestioneFattoriDiConversioneXML {
	
	private static final String ELENCO_FATTORI_DI_CONVERSIONE = "elencoFattoriDiConversione";
	private static final String FATTORE = "fattore";
	private static final String VALORE_FDC = "valoreFDC";
	private static final String FOGLIA1 = "foglia1";
	private static final String FOGLIA2 = "foglia2";
	private static final String RADICE = "radice";
	
	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvaElencoFDCSuXML(String filePath) {
		Document doc = GestioneGeneraleXML.creaFileXML();
		
		Element elementoElencoFDC = doc.createElement(ELENCO_FATTORI_DI_CONVERSIONE);

		doc.appendChild(elementoElencoFDC);

		for(FattoreDiConversione fattore : ElencoFattoriDiConversione.getElencoFattoriDiConversione()) {
			Element elementoFDC = doc.createElement(FATTORE);
			
			Element elementoFoglia1 = doc.createElement(FOGLIA1);

			GestioneGerarchieXML.salvaCategoriaFoglia(fattore.getC1(), doc, elementoFoglia1);
			elementoFDC.appendChild(elementoFoglia1);
			
			elementoFDC.appendChild(GestioneGeneraleXML.creaElemento(doc, VALORE_FDC, String.valueOf(fattore.getValore())));
			
			Element elementoFoglia2 = doc.createElement(FOGLIA2);

			GestioneGerarchieXML.salvaCategoriaFoglia(fattore.getC2(), doc, elementoFoglia2);
			elementoFDC.appendChild(elementoFoglia2);

			elementoElencoFDC.appendChild(elementoFDC);
		}
		GestioneGeneraleXML.salvaFileXML(doc, filePath);
	}

	//PARTE CARICAMENTO ======================================================================================================

	public static void caricaElencoFDCDaXML(String filePath) {
		Document doc = GestioneGeneraleXML.caricaFileXML(filePath);
		doc.getDocumentElement().normalize();
		
		NodeList listaFDC = doc.getElementsByTagName(FATTORE);
		
		for(int i = 0; i < listaFDC.getLength(); i++) {
			Node nodoFattore = listaFDC.item(i);
			if(nodoFattore.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoFattore = (Element) nodoFattore;
		
				double valore = Double.parseDouble(elementoFattore.getElementsByTagName(VALORE_FDC).item(0).getTextContent());
				
				CategoriaFoglia foglia1 = null;
				CategoriaFoglia foglia2 = null;
				
				Node nodoFoglia1 = elementoFattore.getElementsByTagName(FOGLIA1).item(0);
				if(nodoFoglia1.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoFoglia1 = (Element) nodoFoglia1;
					
					String nomeRadice1 = elementoFoglia1.getElementsByTagName(RADICE).item(0).getTextContent();

					CategoriaRadice radice1 = ElencoGerarchie.trovaRadice(nomeRadice1);

					foglia1 = GestioneGerarchieXML.caricaCategoriaFoglia(elementoFoglia1, radice1);
				}

				Node nodoFoglia2 = elementoFattore.getElementsByTagName(FOGLIA2).item(0);
				if(nodoFoglia2.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoFoglia2 = (Element) nodoFoglia2;
					
					String nomeRadice2 = elementoFoglia2.getElementsByTagName(RADICE).item(0).getTextContent();

					CategoriaRadice radice2 = ElencoGerarchie.trovaRadice(nomeRadice2);

					foglia2 = GestioneGerarchieXML.caricaCategoriaFoglia(elementoFoglia2, radice2);
				}
				FattoreDiConversione FDC = new FattoreDiConversione(foglia1, foglia2, valore);
				ElencoFattoriDiConversione.getElencoFattoriDiConversione().add(FDC);
			}
		}
	}
}