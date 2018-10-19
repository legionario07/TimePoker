package timepoker.com.br.timepoker.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Paulo on 25/03/2018.
 */

public class EstruturaBlind {

    private Integer id;
    private String nomeEstrutura;
    private Map<Integer, Integer> mapLevels;
    private Map<Integer, Integer> mapSmalls;
    private Map<Integer, Integer> mapBigs;
    private Map<Integer, Double> mapMins;
    private Map<Integer, Integer> mapAntes;
    private LinkedList<String> nomes;

    public EstruturaBlind(String nomeEstrutura,
                          Map<Integer, Integer> mapLevels,
                          Map<Integer, Integer> mapSmalls,
                          Map<Integer, Integer> mapBigs,
                          Map<Integer, Double> mapMins,
                          Map<Integer, Integer> mapAntes){
        this();
        this.nomeEstrutura = nomeEstrutura;
        this.mapLevels = mapLevels;
        this.mapSmalls = mapSmalls;
        this.mapBigs = mapBigs;
        this.mapMins = mapMins;
        this.mapAntes = mapAntes;

    }

    public EstruturaBlind(){

        mapSmalls = new HashMap<>();
        mapBigs = new HashMap<>();
        mapMins = new HashMap<>();
        mapAntes = new HashMap<>();
        mapLevels = new HashMap<>();

        nomes = new LinkedList<>();
    }

    public Map<Integer, Integer> getMapSmalls() {
        return mapSmalls;
    }

    public void setMapSmalls(Map<Integer, Integer> mapSmalls) {
        this.mapSmalls = mapSmalls;
    }

    public Map<Integer, Integer> getMapBigs() {
        return mapBigs;
    }

    public void setMapBigs(Map<Integer, Integer> mapBigs) {
        this.mapBigs = mapBigs;
    }

    public Map<Integer, Double> getMapMins() {
        return mapMins;
    }

    public void setMapMins(Map<Integer, Double> mapMins) {
        this.mapMins = mapMins;
    }

    public Map<Integer, Integer> getMapAntes() {
        return mapAntes;
    }

    public void setMapAntes(Map<Integer, Integer> mapAntes) {
        this.mapAntes = mapAntes;
    }

    public Map<Integer, Integer> getMapLevels() {
        return mapLevels;
    }

    public void setMapLevels(Map<Integer, Integer> mapLevels) {
        this.mapLevels = mapLevels;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeEstrutura() {
        return nomeEstrutura;
    }

    public void setNomeEstrutura(String nomeEstrutura) {
        this.nomeEstrutura = nomeEstrutura;
    }


    public LinkedList<String> getNomes() {
        return nomes;
    }

    public void setNomes(LinkedList<String> nomes) {
        this.nomes = nomes;
    }

}
