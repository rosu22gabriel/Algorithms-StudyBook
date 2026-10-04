/******************************************************************************
 *  Compilation:  javac GrayscalePicture.java
 *  Execution:    java GrayscalePicture filename
 *  Dependencies: none
 *
 *  Data type for manipulating individual pixels of a grayscale image. The
 *  original image can be read from a file in JPEG, GIF, or PNG format, or the
 *  user can create a blank image of a given dimension. Includes methods for
 *  displaying the image in a window on the screen or saving to a file.
 *
 *  % java GrayscalePicture mandrill.jpg
 *
 *  Remarks
 *  -------
 *   - uses BufferedImage.TYPE_INT_ARGB because BufferedImage.TYPE_BYTE_GRAY
 *     seems to do some undesirable color correction when calling getRGB() and
 *     setRGB()
 *
 ******************************************************************************/

package edu.princeton.cs.algs4;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension; 
import java.awt.FileDialog;
import java.awt.Graphics;  
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


/**
 *  The <code>GrayscalePicture</code> data type provides a basic capability
 *  for manipulating the individual pixels of a grayscale image.
 *  The original image can be read from a {@code PNG}, {@code GIF},
 *  or {@code JPEG} file or the user can create a blank image of a given dimension.
 *  This class includes methods for displaying the image in a window on
 *  the screen or saving it to a file.
 *  <p>
 *  Pixel (<em>col</em>, <em>row</em>) is column <em>col</em> and row <em>row</em>.
 *  By default, the origin (0, 0) is the pixel in the top-left corner.
 *  These are common conventions in image processing and consistent with Java's
 *  {@link java.awt.image.BufferedImage} data type.
 *  The method {@link #setOriginLowerLeft()} change the origin to the lower left.
 *  <p>
 *  The {@code get()} and {@code set()} methods use {@link Color} objects to get
 *  or set the color of the specified pixel. The {@link Color} objects are converted
 *  to grayscale (using the NTSC formula) if the R, G, and B channels are not all equal.
 *  The alpha channel for transparency is preserved.
 *  The {@code getGrayscale()} and {@code setGrayscale()} methods use an
 *  8-bit {@code int} to encode the grayscale value, thereby avoiding the need to
 *  create temporary {@code Color} objects.
 *  <p>
 *  A <em>W</em>-by-<em>H</em> picture uses ~ 4 <em>W H</em> bytes of memory,
 *  since the color of each pixel is encoded as a 32-bit <code>int</code>
 *  <p>
 *  For additional documentation, see
 *  <a href="https://introcs.cs.princeton.edu/31datatype">Section 3.1</a> of
 *  <i>Computer Science: An Interdisciplinary Approach</i>
 *  by Robert Sedgewick and Kevin Wayne.
 *  See {@link Picture} for a version that supports 32-bit ARGB color images.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class GrayscalePicture implements ActionListener {
    private final static boolean HAS_ALPHA = true;

    private BufferedImage image;               // the rasterized image
    private JFrame jframe;                     // on-screen view
    private String title;                      // name of file
    private boolean isOriginUpperLeft = true;  // location of origin
    private boolean isVisible = false;         // is the frame visible?
    private boolean isDisposed = false;        // has the window been disposed?
    private final int width, height;           // width and height

   /**
     * Creates a {@code width}-by-{@code height} picture, with {@code width} columns
     * and {@code height} rows, where each pixel is black.
     *
     * @param width the width of the picture
     * @param height the height of the picture
     * @throws IllegalArgumentException if {@code width} is negative or zero
     * @throws IllegalArgumentException if {@code height} is negative or zero
     */
    public GrayscalePicture(int width, int height) {
        if (width  <= 0) throw new IllegalArgumentException("width must be positive");
        if (height <= 0) throw new IllegalArgumentException("height must be positive");
        this.width  = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

   /**
     * Creates a new grayscale picture that is a deep copy of the argument picture.
     *
     * @param  picture the picture to copy
     * @throws IllegalArgumentException if {@code picture} is {@code null}
     */
    public GrayscalePicture(GrayscalePicture picture) {
        if (picture == null) throw new IllegalArgumentException("constructor argument is null");

        width  = picture.width();
        height = picture.height();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        title = picture.title;
        isOriginUpperLeft = picture.isOriginUpperLeft;
        for (int col = 0; col < width(); col++)
            for (int row = 0; row < height(); row++)
                image.setRGB(col, row, picture.image.getRGB(col, row));
    }

   /**
     * Creates a grayscale picture by reading an image from a file or URL.
     *
     * @param  filename the name of the file (.png, .gif, or .jpg) or URL.
     * @throws IllegalArgumentException if {@code filename} is {@code null}
     * @throws IllegalArgumentException if cannot read image from file or URL
     */
    public GrayscalePicture(String filename) {
        if (filename == null) throw new IllegalArgumentException("constructor argument is null");
        title = filename;
        try {
            // try to read from file in working directory
            File file = new File(filename);
            if (file.isFile()) {
                image = ImageIO.read(file);
            }

            else {

                // resource relative to .class file
                URL url = getClass().getResource(filename);

                // resource relative to classloader root
                if (url == null) {
                    url = getClass().getClassLoader().getResource(filename);
                }

                // or URL from web or jar
                if (url == null) {
                    URI uri = new URI(filename);
                    if (uri.isAbsolute()) url = uri.toURL();
                    else throw new IllegalArgumentException("could not read image: '" + filename + "'");
                }

                image = ImageIO.read(url);
            }

            if (image == null) {
                throw new IllegalArgumentException("could not read image: '" + filename + "'");
            }

            width  = image.getWidth(null);
            height = image.getHeight(null);

            // convert to ARGB if necessary
            if (image.getType() != BufferedImage.TYPE_INT_ARGB) {
                BufferedImage imageARGB = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = imageARGB.createGraphics();
                g.setComposite(AlphaComposite.Src);
                g.drawImage(image, 0, 0, null);
                g.dispose();
                image = imageARGB;
            }

            // convert to grayscale in-place
            for (int col = 0; col < width; col++) {
                for (int row = 0; row < height; row++) {
                    Color color = new Color(image.getRGB(col, row), HAS_ALPHA);
                    Color gray = toGray(color);
                    image.setRGB(col, row, gray.getRGB());
                }
            }
        }
        catch (IOException | URISyntaxException e) {
            throw new IllegalArgumentException("could not open image: " + filename, e);
        }
    }

   /**
     * Creates a picture by reading the image from a JPEG, PNG, GIF, BMP, or TIFF file.
     * The filetype extension must be {@code .jpg}, {@code .png}, {@code .gif},
     * {@code .bmp}, or {@code .tif}.
     *
     * @param file the file
     * @throws IllegalArgumentException if cannot read image
     * @throws IllegalArgumentException if {@code file} is {@code null}
     */         
    public GrayscalePicture(File file) {
        if (file == null) throw new IllegalArgumentException("constructor argument is null");
    
        try {
            image = ImageIO.read(file);
            if (image == null) {
                throw new IllegalArgumentException("could not read image: '" + file + "'");
            }
    
            width  = image.getWidth(null);
            height = image.getHeight(null);
            title = file.getName();

            // convert to ARGB
            if (image.getType() != BufferedImage.TYPE_INT_ARGB) {
                BufferedImage imageARGB = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = imageARGB.createGraphics();
                g.setComposite(AlphaComposite.Src);
                g.drawImage(image, 0, 0, null);
                g.dispose();
                image = imageARGB;
            }

            // convert to grayscale in-place
            for (int col = 0; col < width; col++) {
                for (int row = 0; row < height; row++) {
                    Color color = new Color(image.getRGB(col, row), HAS_ALPHA);
                    Color gray = toGray(color);
                    image.setRGB(col, row, gray.getRGB());
                }
            }
            
        }

        catch (IOException ioe) {
            throw new IllegalArgumentException("could not open file: " + file, ioe);
        }
    }

    // Returns a grayscale version of the given color as a Color object.
    private static Color toGray(Color color) {
        int a = color.getAlpha();
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int y = (int) (Math.round(0.299*r + 0.587*g + 0.114*b));
        return new Color(y, y, y, a);

    }

    private JPanel createViewPanel() {
        return new JPanel() {
                
            @Override
            public Dimension getPreferredSize() {
                // Start at image size, but clamp to ~90% of screen.
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                int maxW = (int) (0.90 * screen.width);
                int maxH = (int) (0.90 * screen.height);
                                    
                double aspect = (double) width / (double) height;
                
                int prefW = Math.min(width, maxW);
                int prefH = (int) Math.round(prefW / aspect);
                if (prefH > maxH) {
                    prefH = Math.min(height, maxH); 
                   prefW = (int) Math.round(prefH * aspect);
                }
                return new Dimension(Math.max(1, prefW), Math.max(1, prefH));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Graphics2D g2 = (Graphics2D) g.create();
                // g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                //                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                                    RenderingHints.VALUE_RENDER_QUALITY);
             
                int availW = getWidth();
                int availH = getHeight();
                 
                // Uniform scale to fit, preserving aspect ratio.
                double sx = (double) availW / width;
                double sy = (double) availH / height;
                double scale = Math.min(sx, sy);
    
                int drawW = (int) Math.round(width  * scale);
                int drawH = (int) Math.round(height * scale);
    
                int x = (availW - drawW) / 2;
                int y = (availH - drawH) / 2;
        
                // Optional: make letterbox bars black
                // (not using for transparent images)
                // g2.setColor(Color.BLACK);
                // g2.fillRect(0, 0, availW, availH);
        
                g2.drawImage(image, x, y, drawW, drawH, null);
                g2.dispose();
            }
        };
    }  

    // create the GUI for viewing the image if needed
    // getMenuShortcutKeyMask() deprecated in Java 10 but its replacement
    // getMenuShortcutKeyMaskEx() is not available in Java 8
    private JFrame createGUI() {
        JFrame frame = new JFrame();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem1 = new JMenuItem(" Save...   ");
        menuItem1.addActionListener(this);
        // Java 11:  use getMenuShortcutKeyMaskEx()
        // Java 8:   use getMenuShortcutKeyMask()
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                 Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menu.add(menuItem1);
        frame.setJMenuBar(menuBar);
                    
        frame.setContentPane(createViewPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle(title);
             
        // window is resizable (preserving aspect ratio)
        frame.setResizable(true);
            
        frame.pack();
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isVisible = false;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                isVisible = false;
                isDisposed = true;
                jframe = null;
            }
        });

        return frame;
    }

   /**
     * Returns a {@link JLabel} containing this picture, for embedding in a {@link JPanel},
     * {@link JFrame} or other GUI widget.
     *
     * @return the {@code JLabel}
     */
    public JLabel getJLabel() {
        if (image == null) return null;         // no image available
        ImageIcon icon = new ImageIcon(image);
        return new JLabel(icon);
    }

   /**
     * Sets the origin to be the upper left pixel. This is the default.
     */
    public void setOriginUpperLeft() {
        isOriginUpperLeft = true;
    }

   /**
     * Sets the origin to be the lower left pixel.
     */
    public void setOriginLowerLeft() {
        isOriginUpperLeft = false;
    }


   /**
     * Displays the picture in a window on the screen.
     */
    public void show() {
        if (isDisposed) return;
        if (jframe == null) jframe = createGUI();
        isVisible = true;
        // Toolkit.getDefaultToolkit().setDynamicLayout(false);
        jframe.setVisible(true);
        jframe.repaint();
    }

   /**
     * Hides the window on the screen.
     */
    public void hide() {
        if (jframe != null) {
            isVisible = false;
            jframe.setVisible(false);
        }
    }

   /**
     * Is the window containing the picture visible?
     * @return {@code true} if the picture is visible, and {@code false} otherwise
     */
    public boolean isVisible() {
        return isVisible;
    }

   /**
     * Returns the height of the picture.
     *
     * @return the height of the picture (in pixels)
     */
    public int height() {
        return height;
    }

   /**
     * Returns the width of the picture.
     *
     * @return the width of the picture (in pixels)
     */
    public int width() {
        return width;
    }

    private void validateRowIndex(int row) {
        if (row < 0 || row >= height())
            throw new IndexOutOfBoundsException("row index must be between 0 and " + (height() - 1) + ": " + row);
    }

    private void validateColumnIndex(int col) {
        if (col < 0 || col >= width())
            throw new IndexOutOfBoundsException("column index must be between 0 and " + (width() - 1) + ": " + col);
    }

    private void validateGrayscaleValue(int gray) {
        if (gray < 0 || gray >= 256)
            throw new IllegalArgumentException("grayscale value must be between 0 and 255");
    }

   /**
     * Returns the grayscale value of pixel ({@code col}, {@code row}) as a {@link java.awt.Color}.
     *
     * @param col the column index
     * @param row the row index
     * @return the grayscale value of pixel ({@code col}, {@code row})
     * @throws IndexOutOfBoundsException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public Color get(int col, int row) {
        validateColumnIndex(col);
        validateRowIndex(row);
        int argb;
        if (isOriginUpperLeft) argb = image.getRGB(col, row);
        else                   argb = image.getRGB(col, height - row - 1);
        Color color = new Color(argb, HAS_ALPHA);
        return toGray(color);
    }

   /**
     * Returns the grayscale value of pixel ({@code col}, {@code row}) as an {@code int}
     * between 0 and 255.
     * Using this method can be more efficient than {@link #get(int, int)} because
     * it does not create a {@code Color} object.
     *
     * @param col the column index
     * @param row the row index
     * @return the 8-bit integer representation of the grayscale value of pixel ({@code col}, {@code row})
     * @throws IndexOutOfBoundsException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public int getGrayscale(int col, int row) {
        validateColumnIndex(col);
        validateRowIndex(row);
        if (isOriginUpperLeft) return image.getRGB(col, row) & 0xFF;
        else                   return image.getRGB(col, height - row - 1) & 0xFF;
    }

   /**
     * Sets the color of pixel ({@code col}, {@code row}) to the given grayscale value.
     *
     * @param col the column index
     * @param row the row index
     * @param color the color (converts to grayscale if color is not a shade of gray)
     * @throws IndexOutOfBoundsException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     * @throws IllegalArgumentException if {@code color} is {@code null}
     */
    public void set(int col, int row, Color color) {
        validateColumnIndex(col);
        validateRowIndex(row);
        if (color == null) throw new IllegalArgumentException("color argument is null");
        Color gray = toGray(color);
        int argb = gray.getRGB();
        if (isOriginUpperLeft) image.setRGB(col, row, argb);
        else                   image.setRGB(col, height - row - 1, argb);
    }

   /**
     * Sets the color of pixel ({@code col}, {@code row}) to the given grayscale value
     * between 0 and 255.
     *
     * @param col the column index
     * @param row the row index
     * @param gray the 8-bit integer representation of the grayscale value
     * @throws IndexOutOfBoundsException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public void setGrayscale(int col, int row, int gray) {
        validateColumnIndex(col);
        validateRowIndex(row);
        validateGrayscaleValue(gray);
        int argb = (0xFF << 24) | (gray << 16) | (gray << 8) | gray;
        if (isOriginUpperLeft) image.setRGB(col, row, argb);
        else                   image.setRGB(col, height - row - 1, argb);
    }

   /**
     * Returns true if this picture is equal to the argument picture.
     *
     * @param other the other picture
     * @return {@code true} if this picture is the same dimension as {@code other}
     *         and if all pixels have the same color; {@code false} otherwise
     */
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        GrayscalePicture that = (GrayscalePicture) other;
        if (this.width()  != that.width())  return false;
        if (this.height() != that.height()) return false;
        for (int col = 0; col < width(); col++)
            for (int row = 0; row < height(); row++)
                if (this.getGrayscale(col, row) != that.getGrayscale(col, row)) return false;
        return true;
    }

   /**
     * Returns a string representation of this picture.
     * The result is a <code>width</code>-by-<code>height</code> matrix of pixels,
     * where the grayscale value of a pixel is an integer between 0 and 255.
     * It does not include the alpha channel.
     *
     * @return a string representation of this picture
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(width +"-by-" + height + " grayscale picture (grayscale values given in hex)\n");
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int gray;
                if (isOriginUpperLeft) gray = 0xFF & image.getRGB(col, row);
                else                   gray = 0xFF & image.getRGB(col, height - row - 1);
                sb.append(String.format("%3d ", gray));
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * This operation is not supported because pictures are mutable.
     *
     * @return does not return a value
     * @throws UnsupportedOperationException if called
     */
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because pictures are mutable");
    }

   /**
     * Saves the picture to a file in either PNG or JPEG format.
     * The filetype extension must be either .png or .jpg.
     *
     * @param  filename the name of the file
     * @throws IllegalArgumentException if {@code filename} is {@code null}
     * @throws IllegalArgumentException if {@code filename} is the empty string
     * @throws IllegalArgumentException if {@code filename} has invalid filetype extension
     * @throws IllegalArgumentException if cannot write the file {@code filename}
     */
    public void save(String filename) {
        if (filename == null) throw new IllegalArgumentException("argument to save() is null");
        save(new File(filename));
        title = filename;
    }

   /**
     * Saves the picture to a file in a PNG or JPEG image format.
     *
     * @param  file the file
     * @throws IllegalArgumentException if {@code file} is {@code null}
     */
    public void save(File file) {
        if (file == null) throw new IllegalArgumentException("argument to save() is null");
        title = file.getName();
        if (jframe != null) jframe.setTitle(title);

        String suffix = title.substring(title.lastIndexOf('.') + 1);
        if (!title.contains(".") || suffix.length() == 0) {
            System.out.printf("Error: the filename '%s' has no file extension, such as .jpg or .png\n", title);
            return;
        }

        try {
            // for formats that support transparency (e.g., PNG and GIF)
            if (ImageIO.write(image, suffix, file)) return;

            // for formats that don't support transparency (e.g., JPG and BMP)
            // create BufferedImage in RGB format and use white background
            BufferedImage imageRGB = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = imageRGB.createGraphics();
            g.drawImage(image, 0, 0, Color.WHITE, null);
            g.dispose();


            if (ImageIO.write(imageRGB, suffix, file)) return;

            // failed to save the file; probably wrong format
            throw new IllegalArgumentException("The filetype '" + suffix + "' is not supported");
        }
        catch (IOException e) {
            throw new IllegalArgumentException("could not write file '" + title + "'", e);
        }
    }

   /**
     * Opens a save dialog box when the user selects "Save As" from the menu.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        FileDialog chooser = new FileDialog(jframe,
                             "The filetype extension must be either .jpg or .png", FileDialog.SAVE);
        chooser.setVisible(true);
        String selectedDirectory = chooser.getDirectory();
        String selectedFilename = chooser.getFile();
        if (selectedDirectory != null && selectedFilename != null) {
            try {
                save(selectedDirectory + selectedFilename);
            }
            catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    } 


   /**
     * Unit tests this {@code Picture} data type.
     * Reads a picture specified by the command-line argument,
     * and shows it in a window on the screen.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        GrayscalePicture picture = new GrayscalePicture(args[0]);
        System.out.printf("%d-by-%d\n", picture.width(), picture.height());
        picture.show();
    }

}

/******************************************************************************
 *  Copyright 2002-2025, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
