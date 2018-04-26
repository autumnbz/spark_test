package wm.rpc_client

import com.jd.jim.cli.{Cluster, ReloadableJimClientFactory}


object JimdbClientStatic {
  private val jimUrl = "jim://2822863144995555107/1258"
  private val clientFactory = new ReloadableJimClientFactory()
  clientFactory.setJimUrl(jimUrl)

  private val client: Cluster = clientFactory.getClient

  def apply(): Cluster = {
    client
  }

  sys.addShutdownHook {
    if (clientFactory != null) {
      clientFactory.clear()
    }
  }
}