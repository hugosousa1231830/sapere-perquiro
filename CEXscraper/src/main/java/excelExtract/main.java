package excelExtract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main (String[] args) throws IOException {

        // LIST OF STORES:
        ArrayList<String> stores = new ArrayList<>();
        stores.add("WRT MOZELOS");
        stores.add("WMB PARQUE NASCENTE");
        stores.add("WRT MAIA JARDIM");
        stores.add("WRT Gaia Jardim");
        stores.add("WRT Arrábida Shop LC");
        stores.add("WMB Gaia");
        stores.add("WMB SANorte Shopping");
        stores.add("WRT Gaia (Rechousa)");
        stores.add("WRT VIA CATARINA");
        stores.add("WRT MAIA");
        stores.add("WRT Matosinhos");
        stores.add("WRT Gaiashopping");

        // LIST OF ITEM TYPES:
        ArrayList<String> types = new ArrayList<>();
        types.add("5203 - Jogos Para Pc");
        types.add("5204 - Jogos Para Consolas");
        types.add("5205 - Acess. Consolas Jogo");
        types.add("5214 - Drones");
        types.add("5302 - Sistemas Audio");
        types.add("5305 - Hardware Foto E Cam");
        types.add("5306 - Ac. Foto/Video");
        types.add("5401 - Portateis");
        types.add("5402 - Tablet e E-Readers");
        types.add("5403 - Desktop");
        types.add("5404 - Impressoras");
        types.add("5407 - Redes");
        types.add("5409 - Armazenamento Dados");
        types.add("5410 - Calculadoras");
        types.add("5501 - Desbloqueados");
        types.add("5709 - Acessorios Tablet");
        types.add(" 5711 - Acessorios Gaming");



        String path = "CEXscraper/07. Listagem Descontinuados 1 a 31 de Julho 2024_wtalk.xlsx";

        List<Item> itemsFromExcel = WRTExcelSource.getSellPrices(stores, types, path);
        List<Item> itemsFromExcelWithCEXPrices = CEXScraper_v2.getCEXinfo(itemsFromExcel);
        OutputExcel.toExcel(itemsFromExcelWithCEXPrices);
    }
}
