package timepoker.com.br.timepoker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import timepoker.com.br.timepoker.dao.EstruturasBlindsDAO;
import timepoker.com.br.timepoker.domain.EstruturaBlind;

public class BlindsActivity extends AppCompatActivity implements View.OnClickListener {

    private EstruturaBlind estruturaBlind;
    private Map<Integer, Integer> mapSmalls;
    private Map<Integer, Integer> mapBigs;
    private Map<Integer, Double> mapMins;
    private Map<Integer, Integer> mapAntes;
    private Map<Integer, Integer> mapLevels;

    private Map<Integer, EditText> mapSmallsView;
    private Map<Integer, EditText> mapBigsView;

    private LinearLayout linearMain;
    private EditText inpNomeEstrutura;

    private EstruturasBlindsDAO dao;

    private static final int inicioViews = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blinds);

        linearMain = findViewById(R.id.lnlMain);
        inpNomeEstrutura = findViewById(R.id.inpNomeEstrutura);
        setOnFocusChangeListener(inpNomeEstrutura);
        findViewById(R.id.imgBtnSalvar).setOnClickListener(this);

        mapSmalls = new HashMap<>();
        mapBigs = new HashMap<>();
        mapMins = new HashMap<>();
        mapAntes = new HashMap<>();
        mapLevels = new HashMap<>();

        mapSmallsView = new HashMap<>();
        mapBigsView = new HashMap<>();

        dao = new EstruturasBlindsDAO(this);

        setDadosDosObjetosDaView();
        getViews();

    }

    private void getDadosDosObjetosDaView() {

        Map<Integer, Integer> mapSmalls = new HashMap<>();
        Map<Integer, Integer> mapBigs = new HashMap<>();
        Map<Integer, Double> mapMins = new HashMap<>();
        Map<Integer, Integer> mapAntes = new HashMap<>();
        Map<Integer, Integer> mapLevels = new HashMap<>();

        int qtdeChilds = linearMain.getChildCount();

        for (int i = inicioViews; i < qtdeChilds; i++) {
            LinearLayout layout = (LinearLayout) linearMain.getChildAt(i);
            for (int j = 0; j < 5; j++) {
                EditText editText = null;
                TextView textView = null;
                if (j == 0) {
                    textView = (TextView) layout.getChildAt(j);
                } else {
                    editText = (EditText) layout.getChildAt(j);
                }

                switch (j) {
                    case 0:
                        //level
                        mapLevels.put(i, Integer.valueOf(textView.getText().toString()));
                        break;

                    case 1:
                        //small
                        mapSmalls.put(i, Integer.valueOf(editText.getText().toString()));
                        break;

                    case 2:
                        //big
                        mapBigs.put(i, Integer.valueOf(editText.getText().toString()));

                        break;

                    case 3:
                        //ante
                        mapAntes.put(i, Integer.valueOf(editText.getText().toString()));
                        break;

                    case 4:
                        //min
                        mapMins.put(i, Double.valueOf(editText.getText().toString()));
                        break;
                }
            }
        }

        String nomeEstrutura = inpNomeEstrutura.getText().toString();
        estruturaBlind = new EstruturaBlind(nomeEstrutura, mapLevels,mapSmalls,mapBigs,mapMins,mapAntes);

    }

    private void setDadosDosObjetosDaView() {

        int qtdeChilds = linearMain.getChildCount();

        for (int i = inicioViews; i < qtdeChilds; i++) {
            LinearLayout layout = (LinearLayout) linearMain.getChildAt(i);
            for (int j = 0; j < 5; j++) {

                int valorInicial = 50;
                Double tempoInicial = Double.valueOf(10);
                int anteInicial = 0;

                EditText editText = null;
                TextView textView = null;

                if (j == 0) {
                    textView = (TextView) layout.getChildAt(j);
                } else {
                    editText = (EditText) layout.getChildAt(j);
                }

                switch (j) {
                    case 0:
                        //level
                        //textView.setText();
                        break;

                    case 1:
                        //small
                        int small = valorInicial * i;
                        editText.setText(String.valueOf(small));
                        break;

                    case 2:
                        //big
                        int big = (valorInicial * i) * 2;
                        editText.setText(String.valueOf(big));

                        break;

                    case 3:
                        //ante
                        editText.setText(String.valueOf(anteInicial));
                        break;

                    case 4:
                        //min
                        editText.setText(tempoInicial.toString());
                        break;
                }
            }
        }
    }

    private void getViews() {

        int qtdeChilds = linearMain.getChildCount();

        for (int i = inicioViews; i < qtdeChilds; i++) {
            LinearLayout layout = (LinearLayout) linearMain.getChildAt(i);
            for (int j = 0; j < 5; j++) {

                EditText editText = null;
                TextView textView = null;

                if (j == 0) {
                    textView = (TextView) layout.getChildAt(j);
                } else {
                    editText = (EditText) layout.getChildAt(j);
                    setOnFocusChangeListener(editText);
                }

                switch (j) {
                    case 0:
                        //level
                        //textView.setText();
                        break;

                    case 1:
                        //small
                        mapSmallsView.put(i, editText);
                        break;

                    case 2:
                        //big
                        mapBigsView.put(i, editText);
                        break;

                    case 3:
                        //ante
                        break;

                    case 4:
                        //min
                        break;
                }
            }
        }
    }


    private void setOnFocusChangeListener(EditText editText) {

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                int idSmall = 1;
                int idBig = 2;

                if (v instanceof EditText) {

                    EditText editText = null;

                    if (((EditText) v).getHint().equals("NOME ESTRUTURA")) {

                        String nomeEstrutura = ((EditText) v).getText().toString();

                        if(nomeEstrutura.isEmpty()){
                            Toast.makeText(BlindsActivity.this, "O nome da estrutura deve ser Preenchido", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    } else {

                        Integer valor = Integer.valueOf(((EditText) v).getText().toString());

                        if (valor == 0) {
                            Toast.makeText(BlindsActivity.this, "Este valor não pode ser 0", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        LinearLayout layout = (LinearLayout) v.getParent();

                        if (((EditText) v).getHint().equals("SMALL")) {

                            editText = (EditText) layout.getChildAt(idBig);
                            valor = valor * 2;
                            editText.setText(String.valueOf(valor));

                        } else if (((EditText) v).getHint().equals("BIG")) {

                            editText = (EditText) layout.getChildAt(idSmall);

                            if (valor % 2 == 1) {
                                valor++;
                                ((EditText) v).setText(String.valueOf(valor));
                            }

                            valor = valor / 2;

                            editText.setText(String.valueOf(valor));

                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.imgBtnSalvar:
                salvar();
                break;
        }

    }

    private void salvar() {

        getDadosDosObjetosDaView();


        Map<Integer, Integer> mapLevels = estruturaBlind.getMapLevels();
        Map<Integer, Integer> mapSmalls = estruturaBlind.getMapSmalls();
        Map<Integer, Integer> mapBigs = estruturaBlind.getMapBigs();
        Map<Integer, Double> mapMins = estruturaBlind.getMapMins();
        Map<Integer, Integer> mapAntes = estruturaBlind.getMapAntes();

        int size = mapLevels.size();

        for(int i = 0; i <size ; i++){
            i++;
            dao.save(estruturaBlind.getNomeEstrutura(),
                    mapLevels.get(i),
                    mapSmalls.get(i),
                    mapBigs.get(i),
                    mapMins.get(i),
                    mapAntes.get(i));
        }

        Toast.makeText(this, "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();


    }
}
