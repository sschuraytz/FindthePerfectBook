package schuraytz.GutdendexBooks;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BookFrameGutendex extends JFrame{

    private final JPanel controls = new JPanel();
    private final JButton loveIt = new JButton("See more about the book");
    private final JButton hateIt = new JButton("Try a different preview");

    private final JPanel searchPanel = new JPanel();
    private JTextField searchTerm_tf = new JTextField();
    private final Image searchButtonImage = ImageIO.read(new URL("http://cdn.onlinewebfonts.com/svg/img_104938.png"));
    private final Image searchButtonImageScaled = searchButtonImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
    private final JButton searchButton = new JButton();

    private JPanel bookInfo = new JPanel();
    private JLabel showBook = new JLabel();
    private JTextArea showBookDetails = new JTextArea();
    private String fullText = "";

    private ArrayList<Integer> randomNumbers = new ArrayList<>();
    private int index = 0;
    private int num;

    private GutendexResponse gutendexResponse;
    private List<Result_Gut> bookList;

    private JScrollPane listScroller;


    public BookFrameGutendex() throws IOException {
        setTitle("Find the Perfect Book");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());

        loveIt.setBackground(Color.GREEN.brighter());
        hateIt.setBackground(Color.RED.brighter());
        controls.add(loveIt);
        controls.add(hateIt);
        root.add(controls, BorderLayout.SOUTH);


        searchTerm_tf.setColumns(20);
        Font font1 = new Font("SansSerif", Font.PLAIN, 13);
        searchTerm_tf.setFont(font1);
        searchTerm_tf.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        searchButton.setIcon(new ImageIcon(searchButtonImageScaled));
        searchButton.setToolTipText("Press this button to start your book search.");

        searchPanel.add(searchTerm_tf);
        searchPanel.add(searchButton);
        searchPanel.setBackground(Color.BLACK);
        root.add(searchPanel, BorderLayout.NORTH);

        bookInfo.setLayout(new BorderLayout());
        bookInfo.add(showBook, BorderLayout.NORTH);
            // this isn't modifying the font
            // bookInfo.setFont(new Font("Times New Roman", Font.BOLD, 25));

       // listScroller.setHorizontalScrollBar(null);

        showBookDetails.setEditable(false);
        bookInfo.add(listScrollerSetUp(), BorderLayout.CENTER);
        root.add(bookInfo, BorderLayout.CENTER);
        setContentPane(root);

        loveIt.addActionListener(e -> {
            if (searchTerm_tf.getText().length() > 0 &&
            searchTerm_tf.getText().matches("[A-Za-z]+")) {
                    loadBookDetailedInfo();
            }
            else {
                getMissingInputMessage();
            }
        });

        hateIt.addActionListener(e -> {
            if (searchTerm_tf.getText().length() > 0 &&
                    searchTerm_tf.getText().matches("[A-Za-z]+")) {
                    num = randomNumbers.get(index);
                    index++;
                try {
                    loadBookBasicInfo();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else {
                showBookDetails.setText("");
                getMissingInputMessage();
            }

        });

        //allow user to just press enter w/o buttons
        searchTerm_tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchButton.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        searchButton.addActionListener(e -> {
            if (searchTerm_tf.getText().length() > 0 &&
                    searchTerm_tf.getText().matches("[A-Za-z]+")) {
                    APICall();
            } else {
                getMissingInputMessage();
            }
        });
    }

    public void loadBookBasicInfo() throws IOException {
        Result_Gut result_gut = bookList.get(num);
        showBook.setText(result_gut.getTitle());

        URL text;

        Formats formats = result_gut.getFormats();
        /*if (formats.getText_plain() != null) {
            text = formats.getText_plain();
        } else*/
        if (formats.getText_html() != null) {
            text = formats.getText_html();
        } else if (formats.getText_plaincharset_us_ascii() != null) {
            text = formats.getText_plaincharset_us_ascii();
        } else if (formats.getText_plaincharset_iso_8859() != null) {
            text = formats.getText_plaincharset_iso_8859();
        } else {
            return;
        }

        readTextFromLink(text);
        showBookDetails.setText(fullText);


    }

    public void loadBookDetailedInfo(){
        List<Author_Gut> authorList = bookList.get(num).getAuthors();
        String authorString;
        if (authorList.size() > 0) {
            authorString = authorList.get(0).getName();
        }
        else {
            authorString = "Author Unknown";
        }

        List<String> subjects = bookList.get(num).getSubjects();
        List<String> shelves = bookList.get(num).getBookshelves();
        showBookDetails.setText("Authors: " + authorString
                + " \nTopics discussed in the book: " + subjects.toString().substring(1, subjects.toString().length()-1)
                + " \nShelves that have this book: " + shelves.toString().substring(1, shelves.toString().length()-1));
    }

    public void APICall() {
        GutendexAPIClient client = new GutendexAPIClient();
        Disposable disposable = client.getBookList(searchTerm_tf.getText())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(books -> {
                    gutendexResponse = books;
                    bookList = gutendexResponse.getResults();

                    createOrderedIntList();
                    randomizeList();
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
        Collections.shuffle(randomNumbers);
    }

    public void readTextFromLink(URL url) throws IOException {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            StringBuilder builder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                builder.append("\n");
                builder.append(inputLine);
            }
            fullText = builder.toString();
            in.close();
    }

    public void getMissingInputMessage() {
        JOptionPane.showMessageDialog(rootPane,
                "You must enter a single word topic or genre " +
                "in the search box to begin your search. \n" +
                "If you did enter a word, it was not found in the system. Please try again.");
    }

    public JScrollPane listScrollerSetUp() {
        listScroller = new JScrollPane(showBookDetails);
        return listScroller;
    }

    public static void main(String[] args) throws IOException {
        new BookFrameGutendex().setVisible(true);
    }
}