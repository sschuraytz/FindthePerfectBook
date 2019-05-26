/*
package schuraytz.books;

import io.reactivex.disposables.Disposable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;

public class BookFrame extends JFrame {
    private final JPanel searchPanel = new JPanel();
    private final JPanel controls = new JPanel();
    private final JButton loveIt = new JButton("Tell me about the book");
    private final JButton hateIt = new JButton("Show me another preview");
    private GoogleBooksResponse bookList;
    private final JTextField searchTerm_tf = new JTextField();
    private final JButton searchButton = new JButton();
    private final JLabel showBook = new JLabel();
    private final JLabel showBookDetails = new JLabel();
    Random rand = new Random();
    private int num;
    private List<Item> item;

    public BookFrame() {
        setTitle("Find the Perfect Book_NY");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());

        controls.add(loveIt);
        controls.add(hateIt);
        root.add(controls, BorderLayout.SOUTH);


        searchPanel.add(searchTerm_tf);
        searchPanel.add(searchButton);
        root.add(searchPanel, BorderLayout.NORTH);

        showBook.setBackground(Color.cyan);
        showBook.setOpaque(true);
        root.add(showBook, BorderLayout.CENTER);


        root.add(showBookDetails);

        setContentPane(root);


        BooksAPIClient client = new BooksAPIClient();
        Disposable disposable = client.getBookList()
                .subscribe(books -> {
                    bookList = books;

                    item = bookList.getItems();
                    num = rand.nextInt(item.size());
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
                num = rand.nextInt(item.size());
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
        showBook.setText(item.get(num).getVolumeInfo().getTitle());
    }

    public void loadBookDetailedInfo() {
        showBookDetails.setText("<html> <p>"+item.get(num).getVolumeInfo().getDescription()+"</p></html>");
    }

    public static void main(String[] args) {
        new BookFrame().setVisible(true);
    }

}
*/


package schuraytz.books;

import io.reactivex.disposables.Disposable;
import retrofit2.http.Url;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
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
    private NYTimesResponse bookList;
    private final JTextField searchTerm_tf = new JTextField();
    private final Image sbI = ImageIO.read(new URL("https://cdn2.iconfinder.com/data/icons/web-solid/32/Artboard_14-512.png"));
    private final Image sbINew = sbI.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
    private final JButton searchButton = new JButton();
    private final JLabel showBook = new JLabel();
    private final JLabel showBookDetails = new JLabel();
    Random rand = new Random();
    private int num;



    private List<Book_NY> bookChoices;

    public BookFrame() throws IOException {
        setTitle("Find the Perfect Book");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());

        controls.add(loveIt);
        controls.add(hateIt);
        root.add(controls, BorderLayout.SOUTH);


        searchTerm_tf.setColumns(10);
        searchPanel.add(searchTerm_tf);
        searchButton.setIcon(new ImageIcon(sbINew));
        searchPanel.add(searchButton);
        root.add(searchPanel, BorderLayout.NORTH);

        showBook.setBackground(Color.cyan);
        showBook.setOpaque(true);
        root.add(showBook, BorderLayout.CENTER);


        root.add(showBookDetails);

        setContentPane(root);


        NYBooksAPIClient client = new NYBooksAPIClient();
        Disposable disposable = client.getBookList()
                .subscribe(books -> {
                    bookList = books;
                    try {
                        String st = bookList.getResults().toString();
                        loveIt.setText(st);
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }


                //    num = rand.nextInt(bookList.size());
              //      loadBookBasicInfo();

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
                //num = rand.nextInt(bookList.getResults()..size());
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
       // showBook.setText(bookList.get(num).getResults().getBooks()..getTitle());
    }

    public void loadBookDetailedInfo() {
    //    showBookDetails.setText("<html> <p>"+bookChoices.get(num).getDescription()+"</p></html>");
    }

    public static void main(String[] args) throws IOException {
        new BookFrame().setVisible(true);
    }

}
