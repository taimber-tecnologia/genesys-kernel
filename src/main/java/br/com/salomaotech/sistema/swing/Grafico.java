package br.com.salomaotech.sistema.swing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static java.util.Objects.isNull;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Grafico {

    private String tituloTopo;
    private String tituloRodape;
    private String legendaEsquerda;
    private final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    private final Map categoriasMap = new HashMap();

    public void setTituloTopo(String tituloTopo) {
        this.tituloTopo = tituloTopo;
    }

    public void setTituloRodape(String tituloRodape) {
        this.tituloRodape = tituloRodape;
    }

    public void setLegendaEsquerda(String legendaEsquerda) {
        this.legendaEsquerda = legendaEsquerda;
    }

    public void addCategoria(BigDecimal valor, String chave) {

        if (!isNull(categoriasMap.get(chave))) {

            valor = valor.add((BigDecimal) categoriasMap.get(chave));

        }

        categoriasMap.put(chave, valor);

    }

    public ChartPanel construir() {

        /* adiciona as categorias */
        categoriasMap.forEach((titulo, valor) -> {

            dataset.addValue(new BigDecimal(String.valueOf(valor)), String.valueOf(titulo), String.valueOf(titulo));

        });

        /* monta o grafico */
        JFreeChart jFreeChart = ChartFactory.createBarChart3D(this.tituloTopo,
                this.tituloRodape,
                this.legendaEsquerda,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel panel = new ChartPanel(jFreeChart);
        return panel;

    }

}
