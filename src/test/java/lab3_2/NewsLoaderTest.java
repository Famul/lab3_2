package lab3_2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.lang.reflect.Field;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.iis.mto.staticmock.Configuration;
import edu.iis.mto.staticmock.ConfigurationLoader;
import edu.iis.mto.staticmock.IncomingInfo;
import edu.iis.mto.staticmock.IncomingNews;
import edu.iis.mto.staticmock.NewsLoader;
import edu.iis.mto.staticmock.NewsReaderFactory;
import edu.iis.mto.staticmock.PublishableNews;
import edu.iis.mto.staticmock.SubsciptionType;
import edu.iis.mto.staticmock.reader.NewsReader;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConfigurationLoader.class, NewsReaderFactory.class})
public class NewsLoaderTest {

    private NewsLoader newsLoader;
    private ConfigurationLoader configurationLoader;
    private Configuration configuration;
    private NewsReader newsReader;
    private IncomingNews incomingNews;
    private Field contentField;

    @Before
    public void setUp() {
        incomingNews = new IncomingNews();

        newsLoader = new NewsLoader();
        mockStatic(ConfigurationLoader.class);
        configurationLoader = PowerMockito.mock(ConfigurationLoader.class);
        when(ConfigurationLoader.getInstance()).thenReturn(configurationLoader);
        configuration = mock(Configuration.class);
        when(configurationLoader.loadConfiguration()).thenReturn(configuration);
        when(configuration.getReaderType()).thenReturn("");
        newsReader = mock(NewsReader.class);
        when(newsReader.read()).thenReturn(incomingNews);
        mockStatic(NewsReaderFactory.class);
        when(NewsReaderFactory.getReader(Mockito.anyString())).thenReturn(newsReader);
    }

    @Test
    public void publishingOnePublicNewsShouldReturnOnlyOnePublicNews() {
        IncomingInfo publicNews = new IncomingInfo("publiczna", SubsciptionType.NONE);
        incomingNews.add(publicNews);
        incomingNews.add(new IncomingInfo("dla subskrybentow", SubsciptionType.A));
        PublishableNews test = new PublishableNews();
        test.addPublicInfo("publiczna");
        test.addForSubscription("dla subskrybentow", SubsciptionType.A);
        Assert.assertThat(newsLoader.loadNews().equals(test), Matchers.equalTo(true));
    }
}
