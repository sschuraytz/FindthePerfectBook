package schuraytz.GutdendexBooks;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookFrameGutendex extends JFrame{

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
    private ArrayList<Integer> randomNumbers = new ArrayList<>();
    private int index = 0;
    private int num;
    private List<Result_Gut> bookList;
    //private int[] randomNumbers = new int[bookList.size() - 1];


    public BookFrameGutendex() throws IOException {
        setTitle("Find the Perfect Book");
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

        //APICall();

        GutendexAPIClient client = new GutendexAPIClient();
        Disposable disposable = client.getBookList()
                .subscribe(books -> {
                    gutendexResponse = books;

                    bookList = gutendexResponse.getResults();
                    num = rand.nextInt(bookList.size());
                   // loadBookBasicInfo();
                });
        createOrderedIntList();
        randomizeList();

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

        searchTerm_tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchTermClass searchClass = new SearchTermClass();
                searchClass.searchTerm = searchTerm_tf.getText();
                loadBookBasicInfo();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchTermClass searchClass = new SearchTermClass();
                searchClass.searchTerm = searchTerm_tf.getText();
                APICall();
                loadBookBasicInfo();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                disposable.dispose();
            }
        });
    }

    public void loadBookBasicInfo() {
        loveIt.setText(bookList.get(num).getTitle());
    }

    public void loadBookDetailedInfo() {
        List<Author_Gut> authorList = bookList.get(num).getAuthors();
        String authorString;
        if (authorList.size() > 0) {
            authorString = authorList.get(0).getName();
        }
        else {
            authorString = "Author Unknown";
        }

        String text;

        if (bookList.get(num).getFormats().getText_plain() != null) {
            text = bookList.get(num).getFormats().getText_plain();
        } else if (bookList.get(num).getFormats().getText_html() != null) {
            text = bookList.get(num).getFormats().getText_html();
        } else if (bookList.get(num).getFormats().getText_plaincharset_us_ascii() != null) {
            text = bookList.get(num).getFormats().getText_plaincharset_us_ascii();
        } else if (bookList.get(num).getFormats().getText_plaincharset_iso_8859() != null) {
            text = bookList.get(num).getFormats().getText_plaincharset_iso_8859();
        } else {
            text = "no text available";
        }

        showBookDetails.setText("<html> <p>" + authorString + " <p>" + text + "</p></html>");
    }

    public void APICall() {
        GutendexAPIClient client = new GutendexAPIClient();
        Disposable disposable = client.getBookList()
                .subscribe(books -> {
                    gutendexResponse = books;

                    bookList = gutendexResponse.getResults();
                    num = randomNumbers.get(index);
                    index++;
                    loadBookBasicInfo();
                });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                disposable.dispose();
            }
        });
    }

    public void createOrderedIntList() {
        for (int i = 0; i < bookList.size(); i++)
        {
            randomNumbers.add(i);
        }
    }

    public void randomizeList() {
        for (int i = 0; i < bookList.size() - 1; i++) {
            int j = rand.nextInt(bookList.size() - 1);
            if (j != i) {
                int temp = randomNumbers.get(i);
                randomNumbers.set(i, randomNumbers.get(j));
                randomNumbers.set(j, temp);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new BookFrameGutendex().setVisible(true);
    }

}