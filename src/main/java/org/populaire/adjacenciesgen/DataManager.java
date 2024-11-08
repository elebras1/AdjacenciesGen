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
    private HashMap<Color, Province> provinces;

    public DataManager() {
        this.provinces = new HashMap<>();
    }

    public void setProvincesCollection(File bmpFile) throws IOException {
        this.provincesImage = ImageIO.read(bmpFile);
        int width = provincesImage.getWidth();
        int height = provincesImage.getHeight();
        int idProvince = 0;
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                int argb = provincesImage.getRGB(i, j);
                int r = ((argb>>16)&0xFF);
                int g = ((argb>>8)&0xFF);
                int b = ((argb)&0xFF);
                int a = ((argb>>24)&0xFF);
                Color color = new Color(r, g, b, a);
                if(!this.provinces.containsKey(color)) {
                    this.provinces.put(color, new Province(idProvince, color));
                    idProvince++;
                }
            }
        }

        System.out.println(this.provinces);
        System.out.println("size : " + this.provinces.size());
    }

    public void loadProvincesDefinitions(File csvFile) throws IOException {
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
            int id = Integer.parseInt(data.get(0));
            int r = Integer.parseInt(data.get(1));
            int g = Integer.parseInt(data.get(2));
            int b = Integer.parseInt(data.get(3));
            Color color = new Color(r, g, b);
            this.provinces.put(color, new Province(id, color));
        }

        System.out.println(this.provinces);
        System.out.println("size : " + this.provinces.size());
    }

}
