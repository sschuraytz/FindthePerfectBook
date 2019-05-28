package schuraytz.GutdendexBooks;

import io.reactivex.disposables.Disposable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Random;

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
    private JLabel showBookDetails = new JLabel();
    private String fullText = "";

    private Random rand = new Random();
    private ArrayList<Integer> randomNumbers = new ArrayList<>();
    private int index = 0;
    private int num;

    private GutendexResponse gutendexResponse;
    private List<Result_Gut> bookList;
    public SearchTermClass searchTermClass = new SearchTermClass();


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

        bookInfo.setLayout(new BoxLayout(bookInfo, BoxLayout.PAGE_AXIS));
        bookInfo.add(showBook);
        bookInfo.setFont(new Font("Sans Serif", Font.BOLD, 14));
        bookInfo.add(showBookDetails);
        root.add(bookInfo, BorderLayout.CENTER);

        setContentPane(root);

        loveIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchTerm_tf.getText().length() > 0) {
                    try {
                        loadBookDetailedInfo();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    getMissingInputMessage();
                }
            }
        });

        hateIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchTerm_tf.getText().length() > 0) {
                    num = rand.nextInt(bookList.size());
                    loadBookBasicInfo();
                    showBookDetails.setText("");
                } else {
                    getMissingInputMessage();
                }
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


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchTerm_tf.getText().length() > 0) {
                    searchTermClass.setSearchTerm(searchTerm_tf.getText());
                    APICall();
                    loadBookBasicInfo();
                } else {
                    getMissingInputMessage();
                }
            }
        });

    }

    public void loadBookBasicInfo() {
        showBook.setText(bookList.get(num).getTitle());
    }

    public void loadBookDetailedInfo() throws IOException {
       /* List<Author_Gut> authorList = bookList.get(num).getAuthors();
        String authorString;
        if (authorList.size() > 0) {
            authorString = authorList.get(0).getName();
        }
        else {
            authorString = "Author Unknown";
        }
        // showBookDetails.setText("<html> <p>" + authorString + " <p>" + text + "</p></html>");*/

        URL text = new URL("http://example.com/pages/");

        if (bookList.get(num).getFormats().getText_plain() != null) {
            text = bookList.get(num).getFormats().getText_plain();
        } else if (bookList.get(num).getFormats().getText_html() != null) {
            text = bookList.get(num).getFormats().getText_html();
        } else if (bookList.get(num).getFormats().getText_plaincharset_us_ascii() != null) {
            text = bookList.get(num).getFormats().getText_plaincharset_us_ascii();
        } else if (bookList.get(num).getFormats().getText_plaincharset_iso_8859() != null) {
            text = bookList.get(num).getFormats().getText_plaincharset_iso_8859();
        }

        readTextFromLink(text);
        showBookDetails.setText("<html><p>" + fullText + "</p> </html>");
        showBookDetails.setPreferredSize(new Dimension(100,100));
    }

    public void APICall() {
        GutendexAPIClient client = new GutendexAPIClient(searchTermClass);
        Disposable disposable = client.getBookList()
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

    public void readTextFromLink(URL text) throws IOException {
        long skippingCounter = 180;
        if (!text.toString().equals("http://example.com/pages/")) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(text.openStream()));

            in.skip(skippingCounter);
            String inputLine;
            int count = 0;
            while ((inputLine = in.readLine()) != null && count <= 250 ) {
                fullText = fullText.concat("\n" + inputLine);
                count++;
            }
            in.close();
        }
    }

    public void getMissingInputMessage() {
        JOptionPane.showMessageDialog(rootPane, "You must enter a topic or genre in the search box" +
                " to begin your search.");
    }

    public static void main(String[] args) throws IOException {
        new BookFrameGutendex().setVisible(true);
    }
}