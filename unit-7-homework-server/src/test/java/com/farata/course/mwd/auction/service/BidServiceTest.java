package com.farata.course.mwd.auction.service;

import com.farata.course.mwd.auction.entity.Bid;
import com.farata.course.mwd.auction.entity.Product;
import com.farata.course.mwd.auction.entity.User;
import com.google.common.collect.Lists;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * @author Igor Baiborodine
 */
@RunWith(Arquillian.class)
public class BidServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(BidServiceTest.class);

    @BeforeClass
    public static void setSystemVar() {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
    }

    @Deployment
    public static Archive<?> deploy() {
        final File[] files =
            Maven.resolver().resolve("com.google.guava:guava:18.0").withoutTransitivity().asFile();
        WebArchive war = ShrinkWrap.create(WebArchive.class, "auction.war")
            .addClasses(Product.class, ProductService.class, BidService.class, Bid.class,
                User.class)
            .addAsManifestResource("beans.xml")
            .addAsLibraries(files);
        logger.info("Created test war[{}]", war.toString());

        return war;
    }

    @Inject
    private ProductService productService;


    private BidService bidService;

    private List<Bid> bids;
    private List<Product> products;
    private List<User> users;

    @Before
    public void setUp() {
        bidService = new BidService();
        products = createProducts();
        users = createUsers();
        bids = createBids();
        bidService.setBids(bids);
    }

    @After
    public void tearDown() {

        bids = null;
        products = null;
        users = null;
    }

    @Test
    public void test() {
        assertThat(productService, notNullValue());
    }

    @Test
    public void findOtherBidders_shouldFindOtherDistinctBiddersForProduct() {

        Bid newBid = new Bid(products.get(0), new BigDecimal("60.00"), 1, users.get(2));
        Set<User> otherBidders = bidService.findOtherBidders(newBid);
        assertThat(otherBidders.size(), equalTo(2));
        assertFalse(otherBidders.contains(users.get(2)));
    }

    private List<Bid> createBids() {

        Bid bid = new Bid(products.get(0), new BigDecimal("10.00"), 1, users.get(0));
        Bid bid2 = new Bid(products.get(0), new BigDecimal("50.00"), 1, users.get(1));
        Bid bid3 = new Bid(products.get(0), new BigDecimal("35.00"), 1, users.get(2));
        Bid bid4 = new Bid(products.get(1), new BigDecimal("80.00"), 1, users.get(0));

        return Lists.newArrayList(bid, bid2, bid3, bid4);
    }

    private List<Product> createProducts() {

        Product product = new Product();
        product.setId(1);
        product.setTitle("Item 1");
        product.setThumbnail("01.jpg");
        product.setDescription("Item 1: Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
        product.setQuantity(15);
        product.setAuctionEndTime(LocalDateTime.now().plusSeconds(60));
        product.setWatchers(5);
        product.setMinimalPrice(new BigDecimal("40.0"));
        product.setReservedPrice(new BigDecimal("200.0"));

        Product product2 = new Product();
        product2.setId(2);
        product2.setTitle("Item 2");
        product2.setThumbnail("02.jpg");
        product2
            .setDescription("Item 2: Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
        product2.setQuantity(5);
        product2.setAuctionEndTime(LocalDateTime.now().plusSeconds(60));
        product2.setWatchers(3);
        product2.setMinimalPrice(new BigDecimal("20.0"));
        product2.setReservedPrice(new BigDecimal("100.0"));

        return Lists.newArrayList(product, product2);
    }

    private List<User> createUsers() {

        User user = new User(1, "philip.fry", "philip.fry@planet-express.com", true);
        User user2 = new User(2, "bender.rodriguez", "bender.rodriguez@planet-express.com", true);
        User user3 = new User(3, "amy.wong", "amy.wong@planet-express.com", true);

        return Lists.newArrayList(user, user2, user3);
    }


}
