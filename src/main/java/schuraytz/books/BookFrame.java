/*
package schuraytz.books;

import io.reactivex.disposables.Disposable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class BookFrame extends JFrame {
    private final JPanel searchPanel = new JPanel();
    private final JPanel controls = new JPanel();
    private final JButton loveIt = new JButton("Tell me about the book");
    private final JButton hateIt = new JButton("Show me another preview");
    private GoogleBooksResponse gutendexResponse;

    private final JTextField searchTerm_tf = new JTextField();
    private final Image searchButtonImage = ImageIO.read(new URL("http://cdn.onlinewebfonts.com/svg/img_104938.png"));
    private final Image searchButtonImageScaled = searchButtonImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
    private final JButton searchButton = new JButton();

    private final JLabel showBook = new JLabel();
    private final JLabel showBookDetails = new JLabel();
    Random rand = new Random();
    private int num;
    private List<Item> bookList;

    public BookFrame() throws IOException {
        setTitle("Find the Perfect Book_NY");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());

        controls.add(loveIt);
        controls.add(hateIt);
        root.add(controls, BorderLayout.SOUTH);


        searchTerm_tf.setColumns(20);
        Font font1 = new Font("SansSerif", Font.PLAIN, 13);
        searchTerm_tf.setFont(font1);
        searchTerm_tf.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        searchButton.setIcon(new ImageIcon(searchButtonImageScaled));

        searchPanel.add(searchTerm_tf);
        searchPanel.add(searchButton);
        searchPanel.setBackground(Color.BLACK);
        root.add(searchPanel, BorderLayout.NORTH);

        showBook.setBackground(Color.cyan);
        showBook.setOpaque(true);
        root.add(showBook, BorderLayout.CENTER);


        root.add(showBookDetails);

        setContentPane(root);


        BooksAPIClient client = new BooksAPIClient();
        Disposable disposable = client.getBookList()
                .subscribe(books -> {
                    gutendexResponse = books;

                    bookList = gutendexResponse.getItems();
                    num = rand.nextInt(bookList.size());
                    loadBookBasicInfo();
                });

        loveIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBookDetailedInfo();
            }
        });

        hateIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num = rand.nextInt(bookList.size());
                loadBookBasicInfo();
                showBookDetails.setText("");
            }
        });


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                disposable.dispose();
            }
        });
    }

    public void loadBookBasicInfo() {
        showBook.setText(bookList.get(num).getVolumeInfo().getTitle());
    }

    public void loadBookDetailedInfo() {
        showBookDetails.setText("<html> <p>"+bookList.get(num).getVolumeInfo().getDescription()+"</p></html>");
    }

    public static void main(String[] args) throws IOException {
        new BookFrame().setVisible(true);
    }

}*/


package schuraytz.books;

import io.reactivex.disposables.Disposable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class BookFrame extends JFrame {
    private final JPanel searchPanel = new JPanel();
    private final JPanel controls = new JPanel();
    private final JButton loveIt = new JButton("Tell me about the book");
    private final JButton hateIt = new JButton("Show me another preview");
    private GutendexResponse gutendexResponse;

    private final JTextField searchTerm_tf = new JTextField();
    private final Image searchButtonImage = ImageIO.read(new URL("http://cdn.onlinewebfonts.com/svg/img_104938.png"));
    private final Image searchButtonImageScaled = searchButtonImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
    private final JButton searchButton = new JButton();

    private final JLabel showBook = new JLabel();
    private final JLabel showBookDetails = new JLabel();
    Random rand = new Random();
    private int num;
    private List<Result_Gut> bookList;

    public BookFrame() throws IOException {
        setTitle("Find the Perfect Book_NY");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());

        controls.add(loveIt);
        controls.add(hateIt);
        root.add(controls, BorderLayout.SOUTH);


        searchTerm_tf.setColumns(20);
        Font font1 = new Font("SansSerif", Font.PLAIN, 13);
        searchTerm_tf.setFont(font1);
        searchTerm_tf.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        searchButton.setIcon(new ImageIcon(searchButtonImageScaled));

        searchPanel.add(searchTerm_tf);
        searchPanel.add(searchButton);
        searchPanel.setBackground(Color.BLACK);
        root.add(searchPanel, BorderLayout.NORTH);

        showBook.setBackground(Color.cyan);
        showBook.setOpaque(true);
        root.add(showBook, BorderLayout.CENTER);


        root.add(showBookDetails);

        setContentPane(root);


        GutendexAPIClient client = new GutendexAPIClient();
        Disposable disposable = client.getBookList()
                .subscribe(books -> {
                    gutendexResponse = books;

                    bookList = gutendexResponse.getResults();
                    num = rand.nextInt(bookList.size());
                   // loadBookBasicInfo();
                });

        loveIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    loadBookDetailedInfo();
            }
        });

        hateIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num = rand.nextInt(bookList.size());
                loadBookBasicInfo();
                showBookDetails.setText("");
            }
        });


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                disposable.dispose();
            }
        });
    }

    public void loadBookBasicInfo() {
        showBook.setText(bookList.get(num).getTitle());
    }

    public void loadBookDetailedInfo() {
        List<Author_Gut> authorList = bookList.get(num).getAuthors();

        String titleString = bookList.get(num).getTitle();
        String authorString = authorList.get(0).getName();

        String text = bookList.get(num).getFormats().getText_plain();

       showBookDetails.setText(text);
    }

    public static void main(String[] args) throws IOException {
        new BookFrame().setVisible(true);
    }

}