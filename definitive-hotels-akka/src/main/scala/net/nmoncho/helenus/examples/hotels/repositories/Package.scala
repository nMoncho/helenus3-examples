package net.nmoncho.helenus.examples.hotels.repositories

import com.datastax.oss.driver.api.core.MappedAsyncPagingIterable
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

def fetchPage[T](prev: Iterator[T], page: MappedAsyncPagingIterable[T])(using ExecutionContext): Future[Iterator[T]] =
    import net.nmoncho.helenus.*

    page.nextPage().flatMap:
        case Some(next, nextPage) =>
            if nextPage.hasMorePages() then fetchPage(prev.concat(next), nextPage)
            else Future.successful(prev.concat(next))

        case None =>
            Future.successful(prev)

end fetchPage
