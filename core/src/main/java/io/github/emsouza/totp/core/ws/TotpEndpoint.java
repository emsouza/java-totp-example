package io.github.emsouza.totp.core.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.time.NtpTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import dev.samstevens.totp.util.Utils;
import io.github.emsouza.totp.core.dto.TotpDto;

@Path("/totp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TotpEndpoint {

    private static final String secret = "RWAMWU7LXQ55HGIXB56UE36HPM2TYNO6";

    @GET
    public String qrCode() throws Exception {
        QrData data = new QrData.Builder().label("example@example.com").secret(secret).issuer("AppName").algorithm(HashingAlgorithm.SHA1).digits(6)
                .period(30).build();

        QrGenerator generator = new ZxingPngQrGenerator();
        byte[] imageData = generator.generate(data);

        String mimeType = generator.getImageMimeType();

        return Utils.getDataUriForImage(imageData, mimeType);
    }

    @POST
    public Response verifica(TotpDto totpDto) throws Exception {

        TimeProvider timeProvider = new NtpTimeProvider("a.st1.ntp.br");
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        DefaultCodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);

        verifier.setAllowedTimePeriodDiscrepancy(2);

        // secret = the shared secret for the user
        // code = the code submitted by the user
        boolean successful = verifier.isValidCode(secret, totpDto.getCode());

        return Response.ok(successful).build();
    }
}
