package it.unibs.view;

import it.unibs.mylib.InputDati;

public class GerarchiaView {
	
	public static void visualizzaNomeFiglioCategoria(String nomePadre, String nomeFiglio, String tipo) {
		System.out.println("Figlio di " + nomePadre + ": " + nomeFiglio + " [" + tipo + "]");
	}
	
	public static boolean richiestaContinuazioneStruttura() {
		return InputDati.yesOrNo("Desideri aggiungere un altro figlio alla Gerarchia?\n");
	}
	
	public static void msgCategoriaGiaEsistente() {
		System.out.println("Esiste gia' una Categoria con questo nome in questa Gerarchia");
	}
	
	//PARTE CATEGORIA RADICE DELLA GERARCHIA =======================================================================================

	public static String inserisciNomeRadiceGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Radice della Gerarchia\n");
	}

	public static void visualizzaNomeRadiceGerarchia(String nomeRadice) {
		System.out.println("\nRadice gerarchia: " + nomeRadice);
	}

	public static void radiceGiaPresente() {
		System.out.println("Esiste già una Categoria Radice con questo nome");
	}

	//PARTE CAMPO-DOMINIO DELLA CATEGORIA ==========================================================================================

	public static String inserisciCampo() {
		return InputDati.leggiStringaNonVuota("Inserisci il Campo della Categoria\n");
	}

	public static String inserisciNomeValoreDominio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del valore del Dominio\n");
	}

	public static void esisteGiaNomeValoreDominio() {
		System.out.println("Esiste gia' un valore del dominio con questo nome");
	}

	public static boolean richiestaNomeValoreDominio() {
		return InputDati.yesOrNo("Desideri aggiungere un altro valore al Dominio?\n");
	}

	public static String inserisciDescrizioneValoreDominio() {
		return InputDati.leggiStringaNonVuota("Inserisci la descrizione del valore del Dominio\n");
	}

	public static boolean richiestaDescrizioneValoreDominio() {
		return InputDati.yesOrNo("Desideri aggiungere una descrizione al valore del dominio?\n");
	}

	//PARTE CATEGORIA FOGLIA =======================================================================================================

	public static boolean richiestaVerificaCategoriaFoglia() {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Foglia?\n");
	}

	public static String inserisciNomeFogliaGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Foglia\n");
	}	

	public static boolean richiestaAggiuntaCategoriaFoglia(String dominioNome) {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Foglia a " + dominioNome + "\n");
	}

	public static String inserisciNomeFogliaRicerca() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Foglia che desideri selezionare\n");
	}	

	public static String inserisciNomeRadiceRicerca() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Radice che desideri selezionare\n");
	}

	public static void fogliaNonTrovata() {
		System.out.println("La Categoria Foglia selezionata non esiste");
	}
	
	//PARTE CATEGORIA NONFOGLIA ====================================================================================================
	
	public static String inserisciNomeNonFogliaGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Non Foglia\n");
	}

	public static boolean richiestaAggiuntaCategoriaNonFoglia(String dominioNome) {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Non Foglia a " + dominioNome + "\n");
	}
}