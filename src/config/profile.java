/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class profile {

    public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = java.nio.file.Paths.get("src/images", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }

    public ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage = null;
        if(ImagePath !=null){
            MyImage = new ImageIcon(ImagePath);
        }else{
            MyImage = new ImageIcon(pic);
        }
        
        int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public void imageUpdater(String destination, String source) {
        try {
            // Create the images directory if it doesn't exist
            File imagesDir = new File("src/images");
            if (!imagesDir.exists()) {
                imagesDir.mkdirs();
            }

            // Create the destination file
            File destFile = new File(destination);
            
            // If destination file exists, delete it first
            if (destFile.exists()) {
                destFile.delete();
            }
            
            // Copy the source file to destination
            Files.copy(new File(source).toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image updated successfully to: " + destination);
        } catch (IOException e) {
            System.out.println("Error occurred while updating the image: " + e);
            throw new RuntimeException("Failed to update image: " + e.getMessage(), e);
        }
    }
}



