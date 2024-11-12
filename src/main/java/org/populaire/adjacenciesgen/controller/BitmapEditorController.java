package org.populaire.adjacenciesgen.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ScrollEvent;
import org.populaire.adjacenciesgen.navigation.SceneManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BitmapEditorController implements Controller {
    private SceneManager sceneManager;
    private File bmpFile;

    @FXML
    private Canvas canvas;
    private double offsetX = 0;
    private double offsetY = 0;
    private double dragStartX;
    private double dragStartY;
    private double zoom = 1;
    private GraphicsContext graphicsContext;

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setBmpFile(File bmpFile) {
        this.bmpFile = bmpFile;
        drawBitmap();
    }

    private void drawBitmap() {
        try {
            Image image = new Image(new FileInputStream(this.bmpFile));

            this.canvas.setWidth(image.getWidth());
            this.canvas.setHeight(image.getHeight());

            this.graphicsContext = this.canvas.getGraphicsContext2D();

            this.drawImageWithOffset(image, this.offsetX, this.offsetY);
            this.initializeMouseEvents(image);
            this.initializeZoomEvent(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeMouseEvents(Image image) {
        this.canvas.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                this.dragStartX = event.getX();
                this.dragStartY = event.getY();
            }
        });

        this.canvas.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                double deltaX = event.getX() - this.dragStartX;
                double deltaY = event.getY() - this.dragStartY;

                this.offsetX += deltaX;
                this. offsetY += deltaY;

                this.drawImageWithOffset(image, this.offsetX, this.offsetY);

                this.dragStartX = event.getX();
                this.dragStartY = event.getY();
            }
        });
    }

    private void initializeZoomEvent(Image image) {
        this.canvas.setOnScroll((ScrollEvent event) -> {
            double zoomFactor = 1.1;
            double oldZoom = this.zoom;

            if (event.getDeltaY() < 0) {
                this.zoom /= zoomFactor;
            } else {
                this.zoom *= zoomFactor;
            }

            this.zoom = Math.max(0.1, Math.min(this.zoom, 10.0));

            double mouseX = event.getX();
            double mouseY = event.getY();

            this.offsetX = (this.offsetX - mouseX) * (this.zoom / oldZoom) + mouseX;
            this.offsetY = (this.offsetY - mouseY) * (this.zoom / oldZoom) + mouseY;

            this.drawImageWithOffset(image, this.offsetX, this.offsetY);
        });
    }

    private void drawImageWithOffset(Image image, double x, double y) {
        this.graphicsContext.setImageSmoothing(false);
        this.graphicsContext.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        this.graphicsContext.save();
        this.graphicsContext.translate(x, y);
        this.graphicsContext.scale(this.zoom, this.zoom);
        this.graphicsContext.drawImage(image, 0, 0);
        this.graphicsContext.restore();
    }

}
