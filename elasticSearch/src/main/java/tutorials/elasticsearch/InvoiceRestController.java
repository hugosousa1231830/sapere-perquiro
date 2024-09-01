package tutorials.elasticsearch;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tutorials.elasticsearch.InvoiceRepository;


@RestController
public class InvoiceRestController {

    @Autowired
    private InvoiceRepository repo;

    @PostMapping("/createOrUpdateInvoice")
    public ResponseEntity<Object> createOrUpdateInvoice(@RequestBody Invoice invoice) throws IOException {
        String response = repo.createOrUpdateInvoice(invoice);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getInvoice")
    public ResponseEntity<Object> getInvoiceById(@RequestParam String invoiceId) throws IOException {
        Invoice invoice = repo.getInvoiceById(invoiceId);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping("/getAllInvoices")
    public ResponseEntity<Object> getAllInvoices() throws IOException {
        List<Invoice> invoices = repo.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @DeleteMapping("/deleteInvoice")
    public ResponseEntity<Object> deleteInvoiceById(@RequestParam String invoiceId) throws IOException {
        String response = repo.deleteInvoiceById(invoiceId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}