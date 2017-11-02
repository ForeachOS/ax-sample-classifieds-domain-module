package com.example.modules.classifieds.installers;

import com.example.modules.classifieds.domain.category.Category;
import com.example.modules.classifieds.domain.category.CategoryRepository;
import com.example.modules.classifieds.domain.classified.Classified;
import com.example.modules.classifieds.domain.classified.ClassifiedRepository;
import com.example.modules.classifieds.domain.classified.Status;
import com.example.modules.classifieds.domain.seller.Seller;
import com.example.modules.classifieds.domain.seller.SellerRepository;
import com.foreach.across.core.annotations.Installer;
import com.foreach.across.core.annotations.InstallerMethod;
import com.foreach.across.core.installers.InstallerPhase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Steven Gentens
 */
@Order(3)
@Installer(description = "Installs a number of classifieds", phase = InstallerPhase.AfterModuleBootstrap)
@RequiredArgsConstructor
public class ClassifiedInstaller {

    private final SellerRepository sellerRepository;
    private final ClassifiedRepository classifiedRepository;
    private final CategoryRepository categoryRepository;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    private List<Seller> sellers;
    private Status[] status;

    private DecimalFormat decimalFormat = new DecimalFormat( "##.##" );

    @InstallerMethod
    private void installSportsAndGear() {
        getRelatedEntities();
        List<Classified> classifieds = new ArrayList<>();
        Category sportsAndGear = categoryRepository.findCategoryByName( "Sports & Gear" );

        if (sportsAndGear != null) {
            classifieds.add(
                    Classified.builder()
                            .category( sportsAndGear )
                            .name( "Mountainbike" )
                            .price( BigDecimal.valueOf( 350.0 ) )
                            .description( "We have a huge variation of second hand bikes with a great range of price. We also have a small selection of childrens' bikes. " +
                                    "We are confident that we have something for you within your budget in our shop to suit your needs like getting to work or university, or to buy as a gift," +
                                    " or to purely enjoy cycling or even to compete alongside others. we also offer repairs and services within the shop - Outdoor Cycles 82A Pwllmelin Road, CF5 2NH. " +
                                    "We are open everyday every week during normal working hours apart from on sundays where we're only open for the mornings. " +
                                    "Felt 6 Seventy. Men's Hardtail MTB. Fully serviced, fully safe and ready to go. \n" )
                            .build() );
            classifieds.add( Classified.builder()
                    .category( sportsAndGear )
                    .name( "Condor Summit Jacket" )
                    .price( BigDecimal.valueOf( 85.0 ) )
                    .description( "The Condor Summit Soft Shell Jacket is designed for comfort and utility. The three layer integrated shell with its water resistant fabrics wicks moisture, " +
                            "while maiting body heat. Equipped with underarm vents for temperature control, reinforcement on the forearms, and multiple pockets for utility and storage make the jacket " +
                            "comfortable and versatile." )
                    .build()
            );

            classifieds.add( Classified.builder()
                    .category( sportsAndGear )
                    .name( "Longboard" )
                    .price( BigDecimal.valueOf( 250.0 ) )
                    .description( "The Sector 9 Green Wave Lookout II longboard is a classic surf cruising and carving board. It has a great blend of flex and rigidity making it nice for throwing out slides " +
                            "and speed checks with ease. The Lookout is a pretty big board with a relatively long wheelbase so it is nice and stable. We really this board because you don't have to go crazy " +
                            "fast to break into slides. It is super fun for just cruising around, doing some cross-stepping, and sliding around when you hit some decent sized hills. " +
                            "For a relatively inexpensive board the Lookout is a ton of fun and great for someone looking to get into longboarding!" )
                    .build() );
        }

        classifieds.forEach( classified -> {
            classifiedRepository.save( classified.toBuilder()
                    .seller( getRandomSeller() )
                    .status( getRandomStatus() )
                    .availableAfter( getRandomDate() )
                    .build() );
        } );

    }

    @InstallerMethod
    private void installMusicAndInstruments() {
        getRelatedEntities();
        Category musicAndInstruments = categoryRepository.findCategoryByName( "Music & Instruments" );
        List<Classified> classifieds = new ArrayList<>();

        if (musicAndInstruments != null) {
            classifieds.add(
                    Classified.builder()
                            .category( musicAndInstruments )
                            .name( "Guitar" )
                            .price( BigDecimal.valueOf( 40.0 ) )
                            .description( "Looking to sell this lovely second hand guitar that I purchased at Glastonbury Festival only two months ago. Was given new strings when I purchased it, " +
                                    "minimally played in my company as sadly been very busy all summer. \n" +
                                    "Looking for a good new home. Collection only. \n" +
                                    "This was listed before and had a lot of interest but I didn't sell it at the time due to a busy schedule." )
                            .build()
            );

            classifieds.add(
                    Classified.builder()
                            .category( musicAndInstruments )
                            .name( "Electronic Drum Kit" )
                            .price( BigDecimal.valueOf( 900.0 ) )
                            .description( "I'm selling my Yamaha DTX562K Electronic Drum Kit. I've owned it for 2 years as a practice kit and it's served me really well. Good for someone beginning to play drums or an experienced drummer who wants a kit to practice with - especially if you want to keep things a little quiet. The kit plays really nicely - I find the silicon DTX pads for snares and toms are really responsive. \n" +
                                    "Also included is a MAPEX stool and Pearl kick pedal which would usually be sold separately. I'll also throw in a pair of virtually unused Goodwood sticks. \n" +
                                    "There's a little wear and tear, but barely noticeable and the kit is fully functional. [There's also a little dust on the cymbal pads in the photos, but i've cleaned this off now - had to take these photos in the middle of a move]. \n" +
                                    "This particular kit sells (without stool and a lower quality pedal) on MusiciansFriend and PMT online for approximately £1200. I am more than willing to negotiate with price :) \n" +
                                    "Any questions welcome if you want to email me! \n" )
                            .build()
            );

            classifieds.add(
                    Classified.builder()
                            .category( musicAndInstruments )
                            .name( "Piano bench" )
                            .price( BigDecimal.valueOf( 175.0 ) )
                            .description( "This second hand but almost as good as new piano bench is a very good quality piece. Duet and adjustable this makes an ideal bench for any age of players and especially for those learning to play along with someone els at the keyboard. \n" +
                                    "Finished in a high gloss black finish with black velvet upholstered seat top." )
                            .build()
            );

            classifieds.forEach( classified -> {
                classifiedRepository.save( classified.toBuilder()
                        .seller( getRandomSeller() )
                        .status( getRandomStatus() )
                        .availableAfter( getRandomDate() )
                        .build() );
            } );
        }
    }

    @InstallerMethod
    private void installElectronics() {
        getRelatedEntities();
        Category electronics = categoryRepository.findCategoryByName( "Electronics" );
        List<Classified> classifieds = new ArrayList<>();
        if (electronics != null) {

            classifieds.add( Classified.builder()
                    .category( electronics )
                    .name( "Canon EOS digital SLR camera" )
                    .price( BigDecimal.valueOf( 880.0 ) )
                    .description( "Expand your photography horizons with the Canon EOS 80D digital SLR camera. Delivering pro-level image quality, " +
                            "this camera helps you achieve razor-sharp details and stunning effects to make the most of your subject. Equipped with a 24.2 megapixels CMOS Sensor " +
                            "the camera is able to create pictures with a resolution of up to 6000 x 4000. The DIGIC 6 image processor ensures a high quality of your images. " +
                            "A big range of features and effects make photos look professional. Te ultra-detailed RAW output capability makes it very easy to balance exposure, saturation, " +
                            "and contrast for amazing pictures every time. If you want to capture moving images, this camera shoots videos in full HD and 24 fps for clear results. " +
                            "The camera also features built-in Wi-Fi for easy transfer and sharing of images." )
                    .build() );

            classifieds.add(
                    Classified.builder()
                            .category( electronics )
                            .name( "Bose CineMate speaker system" )
                            .price( BigDecimal.valueOf( 447.0 ) )
                            .description( "The Bose CineMate II speaker system is the easiest way to enjoy Bose 2.1-channel home theater performance and bring out the full audio potential of your HDTV. " +
                                    "Proprietary TrueSpace technology enables two compact speakers and an Acoustimass module to deliver compelling sound for movies, games, sports and more. " +
                                    "The system easily connects directly to your TV, avoiding the need to disconnect other sources." )
                            .build()
            );

            classifieds.add(
                    Classified.builder()
                            .category( electronics )
                            .name( "Samsung Galaxy S7" )
                            .price( BigDecimal.valueOf( 200.0 ) )
                            .description( "With many connectivity options and a classic design, the Samsung Galaxy S7 is an ideal choice. This mobile phone features 32 GB of internal memory, keeping all of your digital content readily accessible. Ideal for playing games and multitasking, it is equipped with a quad-core processor, so you can open several apps without lag. The phone has a 12-megapixel resolution camera, which will help you snap ultra-detailed pictures. Stay connected with the Samsung Galaxy S7. \n" +
                                    "This item has no scratches, dents, or chips, and comes with charger and original box." )
                            .build()
            );

            classifieds.forEach( classified -> {
                classifiedRepository.save( classified.toBuilder()
                        .seller( getRandomSeller() )
                        .status( getRandomStatus() )
                        .availableAfter( getRandomDate() )
                        .build() );
            } );
        }
    }

    private void getRelatedEntities() {
        sellers = sellerRepository.findAll();
        status = Status.values();
    }

    private Status getRandomStatus() {
        return status[random.nextInt( status.length )];
    }

    private Seller getRandomSeller() {
        return sellers.get( random.nextInt( sellers.size() ) );
    }

    private Date getRandomDate() {
        Date start = getBeforeAfterDate( new Date(), -7 );
        Date end = getBeforeAfterDate( new Date(), 7 );
        return new Date( random.nextLong( start.getTime(), end.getTime() ) );
    }

    private Date getBeforeAfterDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        calendar.add( Calendar.DATE, days );
        return calendar.getTime();
    }
}
