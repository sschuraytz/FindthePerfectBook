package schuraytz.books;

import io.reactivex.disposables.Disposable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private final JPanel controls = new JPanel();
    private final JButton loveIt = new JButton("Tell me about the book");
    private final JButton hateIt = new JButton("Show me another preview");
    private GoogleBooksResponse bookList;
    private final JLabel showBook = new JLabel();
    private final JLabel showBookDetails = new JLabel();
    Random rand = new Random();
    private int num;
    private List<Item> item;

    public BookFrame() {
        setTitle("Find the Perfect Book");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());

        controls.add(loveIt);
        controls.add(hateIt);
        root.add(controls, BorderLayout.SOUTH);

        showBook.setBackground(Color.cyan);
        showBook.setOpaque(true);
        root.add(showBook, BorderLayout.NORTH);
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
