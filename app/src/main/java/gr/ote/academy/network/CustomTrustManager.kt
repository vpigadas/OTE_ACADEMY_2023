package gr.ote.academy.network

import java.security.KeyStore
import java.security.cert.X509Certificate
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class CustomTrustManager : X509TrustManager {

    private val originalManager: X509TrustManager
    private val trustedKeyStore: KeyStore

    init {
        trustedKeyStore = KeyStore.getInstance("PKC512")

        val trustFactory = TrustManagerFactory.getInstance("X509")
        trustFactory.init(trustedKeyStore)

        originalManager = trustFactory.trustManagers[0] as X509TrustManager
    }

    override fun checkClientTrusted(chain: Array<out X509Certificate>?, p1: String?) {

    }

    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        try {
            originalManager.checkServerTrusted(chain, authType)
        } catch (exception: Exception) {
//           val reorderedChain = reorderCertificateChain(chain);
//            CertPathValidator validator = CertPathValidator.getInstance("PKIX");
//            CertificateFactory factory = CertificateFactory.getInstance("X509");
//            CertPath certPath = factory.generateCertPath(Arrays.asList(reorderedChain));
//            PKIXParameters params = new PKIXParameters(trustStore);
//            params.setRevocationEnabled(false);
//            validator.validate(certPath, params)
        }
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()

}