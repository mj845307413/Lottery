package netconn;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.mj.lotterymj.GlobalParams;

public class HttpClientUtils {
	private HttpClient client;
	private HttpPost httpPost;

	public HttpClientUtils() {
		client = new DefaultHttpClient();
		if (StringUtils.isBlank(GlobalParams.PROXY)) {
			HttpHost httpHost = new HttpHost(GlobalParams.PROXY,
					GlobalParams.PORT);// ´úÀí
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					httpHost);
		}
	}

	public InputStream sendXml(String url, String xml) {
		httpPost = new HttpPost(url);
		try {
			StringEntity entity = new StringEntity(xml, "utf-8");
			httpPost.setEntity(entity);
			HttpResponse response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				return response.getEntity().getContent();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
