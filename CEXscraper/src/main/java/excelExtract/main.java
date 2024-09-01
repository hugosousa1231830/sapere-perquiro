package excelExtract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main (String[] args) throws IOException {
        // LIST OF URLS:
        String cexPT = "https://pt.webuy.com/sell/search?stext=";
        String cexES = "https://es.webuy.com/sell/search?stext=";

        // LIST OF STORES:
        ArrayList<String> stores = new ArrayList<>();
        stores.add("WRT Matosinhos");
        /*
        stores.add("WRT NORTE SHOPPING");
        stores.add("WRT LECA PALM MAR SH");
        stores.add("WMB PARQUE NASCENTE");
        stores.add("WRT MAIA JARDIM");
        stores.add("WRT Gaia Jardim");
        stores.add("WRT Arrábida Shop LC");
        stores.add("WMB Gaia");
        stores.add("WMB SANorte Shopping");
        stores.add("WRT Gaia (Rechousa)");
        stores.add("WRT VIA CATARINA");
        stores.add("WRT MAIA");
        stores.add("WRT Gaiashopping");
        stores.add("WRT ALAMEDA SHOPPPING");
        stores.add("WRT ERMESINDE");
        stores.add("WRT Fanzeres");
        stores.add("WRT MIRA MAIA");
        stores.add("WRT S.Cosme");
        stores.add("WRT S.FELIX MARINHA");
        stores.add("WRT SM DA FEIRA");
        stores.add("WRT VALONGO C");
        stores.add("WRT Vila Real");
        stores.add("WRT MOZELOS");
        stores.add("WRT AVINTES");

         */

        // LIST OF ITEM TYPES:
        ArrayList<String> types = new ArrayList<>();
        types.add("5201 - Consolas");
        types.add("5204 - Jogos Para Consolas");
        types.add("5205 - Acess. Consolas Jogo");
        types.add("5210 - Mobilidade");
        types.add("5214 - Drones");
        types.add("5301 - Imagem");
        types.add("5302 - Sistemas Audio");
        types.add("5401 - Portateis");
        types.add("5402 - Tablet e E-Readers");
        types.add("5404 - Impressoras");
        types.add("5406 - Mon + Vproject");
        types.add("5407 - Redes");
        types.add("5408 - Componentes");
        types.add("5409 - Armazenamento Dados");
        types.add("5501 - Desbloqueados");
        types.add("5502 - Acessorios Telecom");
        types.add("5505 - Domotica");
        types.add("5704 - Eletr. Desportiva");
        types.add("5710 - Acessorios Pc");
        types.add("5711 - Acessorios Gaming");
        types.add("6802 - Nos");
        types.add("6803 - Vodafone");


        String path = "CEXscraper/07. Listagem Descontinuados 1 a 31 de Julho 2024_wtalk.xlsx";

        List<Item> itemsFromExcel = WRTExcelSource.getSellPrices(stores, types, path);
        List<Item> itemsFromExcelWithCEXPrices = CEXScraper_v3.getCEXinfo(itemsFromExcel,cexPT);
        OutputExcel.toExcel(itemsFromExcelWithCEXPrices);
    }
}
