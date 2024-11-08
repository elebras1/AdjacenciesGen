package org.populaire.adjacenciesgen;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataManager {
    private BufferedImage provincesImage;
    private Map<Color, Province> provinces;
    private Map<Province, Set<Short>> adjacencies;

    public DataManager() {
        this.provinces = new HashMap<>();
        this.adjacencies = new HashMap<>();
    }

    public void readBitmapFile(File bmpFile) throws IOException {
        this.provincesImage = ImageIO.read(bmpFile);
    }

    public void setProvincesCollectionByBitmap(){
        int width = this.provincesImage.getWidth();
        int height = this.provincesImage.getHeight();
        short idProvince = 0;
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                Color color = this.getColorByPosition(i, j);
                if(!this.provinces.containsKey(color)) {
                    this.provinces.put(color, new Province(idProvince, color));
                    idProvince++;
                }
            }
        }
    }

    public void setProvincesCollectionByDefinitionsCsv(File csvFile) throws IOException {
        List<List<String>> csvData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if(values.length == 0) {
                    values = line.split(",");
                }
                csvData.add(Arrays.asList(values));
            }
        }

        for(List<String> data : csvData) {
            short id = Short.parseShort(data.get(0));
            int r = Integer.parseInt(data.get(1));
            int g = Integer.parseInt(data.get(2));
            int b = Integer.parseInt(data.get(3));
            Color color = new Color(r, g, b);
            this.provinces.put(color, new Province(id, color));
        }
    }

    public void setAdjacenciesCollection() {
        int width = this.provincesImage.getWidth();
        int height = this.provincesImage.getHeight();
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = this.getColorByPosition(i, j);
                Province province = this.provinces.get(color);
                if (province != null) {
                    if(!this.adjacencies.containsKey(province)) {
                        this.adjacencies.put(province, new HashSet<>());
                    }
                    if (i > 0) {
                        this.addAdjacencies(province, i - 1, j);
                    }
                    if (i < width - 1) {
                        this.addAdjacencies(province, i + 1, j);
                    }
                    if (j > 0) {
                        this.addAdjacencies(province, i, j - 1);
                    }
                    if (j < height - 1) {
                        this.addAdjacencies(province, i, j + 1);
                    }
                }
            }
            System.out.println("Province calculated : " + this.adjacencies.size() + " / " + this.provinces.size());
        }
        System.out.println("Adjacencies : " + this.adjacencies);
    }

    public void addAdjacencies(Province ProvinceNode, int x, int y) {
        Color colorBottom = this.getColorByPosition(x, y);
        Province provinceAdjacent = this.provinces.get(colorBottom);
        if (provinceAdjacent != null) {
            this.adjacencies.get(ProvinceNode).add(provinceAdjacent.getId());
        }
    }

    public Color getColorByPosition(int x, int y) {
        int argb = this.provincesImage.getRGB(x, y);
        int r = ((argb >> 16) & 0xFF);
        int g = ((argb >> 8) & 0xFF);
        int b = ((argb) & 0xFF);
        return new Color(r, g, b, 255);
    }

    public int getNumberProvinces() {
        return this.provinces.size();
    }
}
