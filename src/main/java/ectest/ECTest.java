package ectest;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.InvoiceObj;

public class ECTest {


  public static void main(String[] args) {

    AioCheckOutALL coa = new AioCheckOutALL();
    AllInOne aio = new AllInOne("");
    InvoiceObj io = new InvoiceObj();

    io.setCustomerEmail("nilm987521@gmail.com");
    io.setCustomerID("1");
    io.setInvoiceItemCount("2");
    io.setInvoiceItemName("ABCD123");
    io.setInvoiceItemPrice("456");
    io.setInvoiceItemWord("個");
    io.setInvoiceRemark("測試");
    aio.aioCheckOut(coa, io);
    aio.aioCheckOutFeedback(null);


  }
}
