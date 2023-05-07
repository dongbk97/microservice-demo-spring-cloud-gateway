package com.example.exportfile.factory;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class ExportFile {
    private final Logger logger = getLog();

    public static Logger getLog() {
        return log;
    }

    public UploadFileRequest exportDrbAuto(/*LoanSuggestionEntity loanSuggestionExist,
                                           LimitEntity limitExist, LoanAccountEntity loanAccountExist,
                                           CustomerEntity customerExist, LoanContractEntity loanContractExist,
                                           PaymentInstructionEntity paymentInstructionExist,
                                           List<FundamentalPaymentEntity> fundamentalPaymentList,
                                           List<PayrollBulkEntity> payrollBulkList, List<DocumentRefundEntity> documentRefundList,*/
            String nfRate, String drbTemplatePath, String drbTemplateFileName,
            boolean isCnvtDrbToDoc/*, User user*/) {
        try {
            Configuration cfg = new Configuration();
            cfg.setIncompatibleImprovements(new Version(2, 3, 20));
            FileTemplateLoader templateLoader = new FileTemplateLoader(new File(drbTemplatePath));
            cfg.setTemplateLoader(templateLoader);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setLocale(Locale.US);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate(drbTemplateFileName);
//            List<RelLoanRateEntity> relLoanRateList = relLoanRateService.view((loanAccountExist != null ? loanAccountExist.getId() : 0));
            Map<String, Object> input = new HashMap<>();
            input.put("fontFamily", drbTemplatePath + "fonts/Roboto-Regular.ttf");
            defaultInput(input);
//            genSchedules(loanSuggestionExist, loanAccountExist, fundamentalPaymentList, payrollBulkList, documentRefundList, input, user);
//            genDataPdf(loanSuggestionExist, limitExist, loanAccountExist, customerExist, loanContractExist, relLoanRateList, nfRate, input);
            hideConvertDrbDoc(input, isCnvtDrbToDoc);
//            logger.info("Input Tlf: {}", JsonUtil.toString(input));
            return outputDrb(drbTemplatePath, drbTemplateFileName, template, input);
        } catch (Exception exception) {
            logger.error("Exception occured while processing freeMarker template: {} ", exception.getMessage(), exception);
        }
        return null;
    }

    public UploadFileRequest exportDrbAutoIb(/*ExportDrbRequest exportDrbRequest,*/ String userName) throws IOException {
        /*LoanSuggestionEntity loanSuggestionExist = exportDrbRequest.getLoanSuggestionExist();
        LimitEntity limitExist = limitService.findByLimitNo(loanSuggestionExist.getLimitNo()).orElse(null);
        LoanContractEntity loanContractExist = loanContractService.findByContractNo(loanSuggestionExist.getContractNo()).orElse(null);
        List<DocumentRefundEntity> documentRefundList = exportDrbRequest.getDocumentRefundList();
        PaymentInstructionEntity paymentInstructionExist = exportDrbRequest.getPaymentInstructionExist();
        List<FundamentalPaymentEntity> fundamentalPaymentList = exportDrbRequest.getFundamentalPaymentList();
        List<PayrollBulkEntity> payrollBulkList = exportDrbRequest.getPayrollBulkList();
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String accessToken = details.getTokenValue();
        user.setAccessToken(accessToken);
        user.setUsername(authentication.getName());
        CustomerEntity customerExist = customerService.findByCifNoExist(exportDrbRequest.getLoanSuggestionExist().getCifNo()).orElse(null);
        Setting setting = settingRepository.uniqueName(RmConstant.FILE_STORAGE).orElse(null);
        String drbTemplatePath = setting.getValue() + "gnn/";
        logger.info("templatePath: {}", drbTemplatePath);*/
        return exportDrbAuto(
                /*loanSuggestionExist, limitExist, null,
                customerExist, loanContractExist,
                paymentInstructionExist, fundamentalPaymentList,
                payrollBulkList, documentRefundList, "",
                drbTemplatePath,*/ "drbCustomerAuto.ftl", "", "",
                false/*, user*/);
    }

//    private void genSchedules(LoanSuggestionEntity loanSuggestionExist,
//                              LoanAccountEntity loanAccountExist,
//                              List<FundamentalPaymentEntity> fundamentalPaymentList,
//                              List<PayrollBulkEntity> payrollBulkList,
//                              List<DocumentRefundEntity> documentRefundList,
//                              Map<String, Object> input, User user) {
//        Locale locale = new Locale("vi", "VN");
//        if (loanSuggestionExist.getPaymentMethod() != null) {
//            switch (loanSuggestionExist.getPaymentMethod().toUpperCase()) {
//                case RmConstant.CHARGES_PAID_BY_CSH:
//                case RmConstant.CHARGES_PAID_BY_CASH:
//                    input.put("tfr", "d-none");
//                    break;
//                default:
//                    input.put("csh", "d-none");
//            }
//        }
//        String documentRefundBody = "";
//        List<DocumentTypeEntity> documentTypeList = settingService.getListDocType(user);
//        for (DocumentRefundEntity documentRefundExist : documentRefundList) {
//            documentRefundBody += "<tr>\n" + " <td style=\"border: 1px dashed black\">" + settingService.getDocTypeNameById(maybeNull(documentRefundExist.getDocumentType()), documentTypeList) + "</td>\n" + " <td style=\"border: 1px dashed black\">" +
//                    maybeNull(documentRefundExist.getQuantity()) + "</td>\n" +
//                    " <td style=\"border: 1px dashed black\">" +
//                    convertDate2String(documentRefundExist.getBankRefundTime(), "dd-MM-yyyy") +
//                    "</td>\n" + "</tr>";
//
//            input.put("documentRefund", documentRefundBody);
//            // Payment
//            switch (loanSuggestionExist.getDisbursementPurpose()) {
//                case RmConstant.DISBURSEMENT_PURPOSE.OTHER:
//                case RmConstant.DISBURSEMENT_PURPOSE.DMT:
//                    input.put("paymentSalary", "d-none");
//                    input.put("paymentLC", "d-none");
//                    String fundDamentalBody = "";
//                    int index = 0;
//                    for (FundamentalPaymentEntity fundamentalPaymentEntity : fundamentalPaymentList) {
//                        index++;
//                        if (fundamentalPaymentEntity.getStatus().equals(RmConstant.STATUS.APRV)) {
//                            fundDamentalBody += " <tr>\n" + " <td class=\"text-center\" style=\"border: 1px dashed black\">" +
//                                    index + "</td>\n" + " <td class=\"text-right\" style=\"border: 1px dashed black\">" +
//                                    NumberFormat.getInstance(locale).format(fundamentalPaymentEntity.getAmount()) +
//                                    "</td>\n" + " <td class=\"text-center\" style=\"border: 1px dashed black\">" +
//                                    escapeXml(maybeNull(fundamentalPaymentEntity.getBnfName())) + "</td>\n" +
//                                    " <td class=\"text-center\" style=\"border: 1px dashed black\">" +
//                                    escapeXml(!maybeNull(fundamentalPaymentEntity.getBnfBrnName()).isEmpty() ? fundamentalPaymentEntity.getBnfBrnName() : "MARITIME BANK - NH TMCP HANG HAI VIET NAM (MSB)") +
//                                    "</td>\n" + " <td class=\"text-center\" style=\"border: 1px dashed black\">" +
//                                    maybeNull(fundamentalPaymentEntity.getBnfAccount()) + "</td>\n" + " <td style=\"border: 1px dashed black\">" +
//                                    escapeXml(maybeNull(fundamentalPaymentEntity.getRemark())) + "</td>\n" + " <td style=\"border: 1px dashed black\">" +
//                                    convertFeeBearer(fundamentalPaymentEntity.getFeeBearer()) + "</td>\n" + " </tr>";
//                        }
//                    }
//                    input.put("fundamentalPaymentTable", fundDamentalBody);
//                    break;
//
//                case RmConstant.DISBURSEMENT_PURPOSE.SAL:
//                    input.put("paymentDomestic", "d-none");
//                    input.put("paymentLC", "d-none");
//                    String prBulkBody = "";
//                    int indexSal = 0;
//                    if (payrollBulkList != null) {
//                        for (PayrollBulkEntity prBulk : payrollBulkList) {
//                            indexSal++;
//                            if (!prBulk.getStatus().equals(RmConstant.STATUS.REJT)) {
//                                prBulkBody += " <tr>\n" + " <td class=\"text-center\" style=\"border: 1px dashed black\">" +
//                                        indexSal + "</td>\n" + " <td style=\"border: 1px dashed black\">" + "Kỳ lương tháng " +
//                                        maybeNull(prBulk.getSalaryTerm()) + " - Bộ phận " + prBulk.getDepartment() + "</td>\n" +
//                                        " <td class=\"text-right\" style=\"border: 1px dashed black\">" +
//                                        NumberFormat.getInstance(locale).format(prBulk.getBulkAmount()) + "</td>\n" +
//                                        " <td class=\"text-center\" style=\"border: 1px dashed black\">" +
//                                        (prBulk.getCcy() != null ? prBulk.getCcy() : "VND") + "</td>\n" +
//                                        " <td class=\"text-center\" style=\"border: 1px dashed black\">" +
//                                        "Theo Bảng lương đính kèm Đề nghị vay - " + convertDateHHmmDD(loanSuggestionExist.getCreateDate()) +
//                                        "</td>\n" + " <td style=\"border: 1px dashed black\">" +
//                                        "Người chuyển trả" + "</td>\n" + " </tr>";
//                            }
//                        }
//                        input.put("fundamentalPaymentTableSal", prBulkBody);
//                    }
//                    break;
//                case RmConstant.DISBURSEMENT_PURPOSE.LC:
//                    input.put("paymentDomestic", "d-none");
//                    input.put("paymentSalary", "d-none");
//
//                case RmConstant.DISBURSEMENT_PURPOSE.LC:
//                    input.put("paymentDomestic", "d-none");
//                    input.put("paymentSalary", "d-none");
//                    String fundDamentalBodyLc = "";
//                    int indexLc = 0;
//                    for (FundamentalPaymentEntity fundamentalPaymentEntity : fundamentalPaymentList) {
//                        indexLc++;
//                        if (fundamentalPaymentEntity.getStatus().equals(RmConstant.STATUS.APRV)) {
//                            fundDamentalBodyLc += " <tr>\n" + " <td class=\"text-center\" style=\"border: 1px dashed black\">" +
//                                    indexLc + "</td>\n" + " <td class=\"text-right\" style=\"border: 1px dashed black\">" +
//                                    NumberFormat.getInstance(locale).format(fundamentalPaymentEntity.getAmount()) + "</td>\n" +
//                                    " <td class=\"text-center\" style=\"border: 1px dashed black\">" +
//                                    maybeNull(fundamentalPaymentEntity.getCcy()) + "</td>\n" + " <td style=\"border: 1px dashed black\">" +
//                                    maybeNull(fundamentalPaymentEntity.getSourceBank()) + "</td>\n" +
//                                    " <td class=\"text-center\" style=\"border: 1px dashed black\">" +
//                                    maybeNull(fundamentalPaymentEntity.getRefNo()) + "</td>\n" +
//                                    " <td style=\"border: 1px dashed black\">" + escapeXml(maybeNull(fundamentalPaymentEntity.getBnfName())) +
//                                    "</td>\n" + " <td style=\"border: 1px dashed black\">" +
//                                    escapeXml(maybeNull(fundamentalPaymentEntity.getBnfBrnName())) + "</td>\n" +
//                                    " <td style=\"border: 1px dashed black\">" + maybeNull(fundamentalPaymentEntity.getBnfAccount()) +
//                                    "</td>\n" + " <td style=\"border: 1px dashed black\">" + escapeXml(maybeNull(fundamentalPaymentEntity.getRemark())) +
//                                    "</td>\n" + " <td style=\"border: 1px dashed black\">" + convertFeeBearer(fundamentalPaymentEntity.getFeeBearer()) +
//                                    "</td>\n" + " </tr>";
//                        }
//                    }
//                    input.put("fundamentalPaymentTableLc", fundDamentalBodyLc);
//                    break;
//            }
//        }
//    }


//    private void genDataPdf(LoanSuggestionEntity loanSuggestionExist,
//                            LimitEntity limitExist, LoanAccountEntity loanAccountExist,
//                            CustomerEntity customerExist, LoanContractEntity loanContractExist,
//                            List<RelLoanRateEntity> relLoanRateList,
//                            String nfRate, Map<String, Object> input) {
//        String mnRateRevTermUnit = "";
//        String mnRateReviewDate = "";
//        String margin = "";
//        BigDecimal totalAmoutApprove = loanSuggestionExist.getAprvAmount();
//        Date currentDate = new Date();
//        String minuteCvt = Utils.convertDate2String(currentDate, "mm");
//        String hourCvt = Utils.convertDate2String(currentDate, "HH");
//        String dateCvt = Utils.convertDate2String(currentDate, "dd");
//        String monthCvt = Utils.convertDate2String(currentDate, "MM");
//        String yearCvt = Utils.convertDate2String(currentDate, "yyyy");
//        // set value input to ftl
//        Locale locale = new Locale("vi", "VN");
//        input.put("disbursementPurpose", convertDisburPurpose(maybeNull(loanSuggestionExist.getDisbursementPurpose())));
//        input.put("sugsNo", maybeNull(loanSuggestionExist.getSugsNo()));
//        input.put("limitName", escapeXml(maybeNull(limitExist.getLimitName())));
//        input.put("createdDate", (Utils.convertDate2String((loanSuggestionExist.getCreateDate() != null ? loanSuggestionExist.getCreateDate() : new Date()), "dd-MM-yyyy")));
//        input.put("contractNo", maybeNull((loanContractExist != null ? loanContractExist.getContractNo() : "")));
//        input.put("effectiveDate", Utils.convertVNDate((loanContractExist != null ? loanContractExist.getEffectiveDate() : null)));
//        input.put("customerName", escapeXml(maybeNull(customerExist.getCustomerName())));
//        input.put("customerIbName", escapeXml(maybeNull(loanSuggestionExist.getModifyBy())));
//
//// 3
//        input.put("cifNo", maybeNull(customerExist.getCifNo()));
//        // 4
//        input.put("customerIdNo", maybeNull(customerExist.getIdNo()));
//        input.put("cusCertCode", escapeXml(maybeNull(loanSuggestionExist.getCusCertCode())));
//        input.put("issuePlace", escapeXml(maybeNull(customerExist.getIssuePlace())));
//        input.put("issueDate", Utils.convertDate2String(customerExist.getIssueDate(), "dd-MM-yyyy"));
//        // 5
//        input.put("numberAmountCus", NumberFormat.getInstance(locale).format(loanSuggestionExist.getAmount()) + " " + loanSuggestionExist.getCcy());
//        input.put("wordAmountCus", Utils.ConvertMoney(String.valueOf(loanSuggestionExist.getAmount())) + " " + convertVNDCcy(loanSuggestionExist.getCcy()));
//        input.put("numberAmount", NumberFormat.getInstance(locale).format(loanSuggestionExist.getAprvAmount()) + " " + loanSuggestionExist.getCcy());
//        input.put("wordAmount", Utils.ConvertMoney(String.valueOf(loanSuggestionExist.getAprvAmount())) + " " + convertVNDCcy(loanSuggestionExist.getCcy()));
//        // 7 TODO
//        input.put("createLoanSugHour", (Utils.convertDate2String((loanSuggestionExist.getCreateDate() != null ? loanSuggestionExist.getCreateDate() : new Date()), "HH")));
//        input.put("createLoanSugMinute", (Utils.convertDate2String((loanSuggestionExist.getCreateDate() != null ? loanSuggestionExist.getCreateDate() : new Date()), "mm")));
//        input.put("createLoanSugDate", (Utils.convertDate2String((loanSuggestionExist.getCreateDate() != null ? loanSuggestionExist.getCreateDate() : new Date()), "dd-MM-yyyy")));
//        // 8 -> 2 // 9 -> 2,4 // THONG TIN GIAI NGAN // 10 // 11
//        input.put("loanTerm", (maybeNull(loanSuggestionExist.getTerm()) + maybeNull(loanSuggestionExist.getTermUnit()).replace(RmConstant.FREQ_CODE.DAY, " ngày").replace(RmConstant.FREQ_CODE.MONTH, " tháng").replace(RmConstant.FREQ_CODE.YEAR, " năm")));
//
//
//        input.put("disbursementHour", (loanSuggestionExist.getDisbursementDate() != null ? Utils.convertDate2String(loanSuggestionExist.getDisbursementDate(), "HH") : ""));
//        input.put("disbursementMinute", (loanSuggestionExist.getDisbursementDate() != null ? Utils.convertDate2String(loanSuggestionExist.getDisbursementDate(), "mm") : ""));
//        input.put("disbursementDate", (Utils.convertDate2String((loanSuggestionExist.getDisbursementDate()), "dd-MM-yyyy")));
//        // 14
//        input.put("mnRateRevTermUnit", mnRateRevTermUnit.replace(RmConstant.FREQ_CODE.DAY, " ngày").replace(RmConstant.FREQ_CODE.MONTH, " tháng").replace(RmConstant.FREQ_CODE.YEAR, " năm"));
//        // 20
//        input.put("mnRateReviewDate", mnRateReviewDate);
//        // 21 // 26
//        input.put("customerNamePay", loanSuggestionExist.getPaymentMethod().equals(RmConstant.PAY_METHOD.CSH) ? "MSB" : escapeXml(maybeNull(customerExist.getCustomerName())));
//        input.put("taxCode", maybeNull(customerExist.getTaxCode()));
//        input.put("totalAmoutApprove", totalAmoutApprove != null ? NumberFormat.getInstance(locale).format(totalAmoutApprove) + loanSuggestionExist.getCcy() : "");
//        input.put("approvalLoanDate", convertDate2String(loanSuggestionExist.getDisbursementDate() != null ? loanSuggestionExist.getDisbursementDate() : currentDate, "dd-MM-yyyy"));
//        input.put("convertDrbDoc", hourCvt + " giờ " + minuteCvt + " phút " + "ngày " + dateCvt + " tháng " + monthCvt + " năm " + yearCvt);
//    }

    public void hideConvertDrbDoc(Map<String, Object> input, boolean isCnvtDrbToDoc) {
        if (!isCnvtDrbToDoc) {
            input.put("convertDrbDocId", "d-none");
        }
    }

    private UploadFileRequest outputDrb(String drbTemplatePath, String drbTemplateFileName,
                                        Template template, Map<String, Object> input) {
        try {
            String outputHtmlPath = drbTemplatePath + "drb/";
            File outHtmlPathFolder = new File(outputHtmlPath);
            if (!outHtmlPathFolder.exists()) {
                outHtmlPathFolder.mkdir();
            }
            // save html file
            String fileHtmlName = /*FilenameUtils.getBaseName(drbTemplateFileName) + System.currentTimeMillis() */ "fileExport" + ".html";
            String outputHtmlName = outputHtmlPath + fileHtmlName;
            Writer fileWriter = new OutputStreamWriter(new FileOutputStream(outputHtmlName), StandardCharsets.UTF_8);
            template.process(input, fileWriter);
            fileWriter.close();
            // save pdf file
            String filePdfName = outputHtmlPath /*+ FilenameUtils.getBaseName(fileHtmlName)*/ + ".pdf";
            OutputStream os = new FileOutputStream(filePdfName);
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withUri("file://" + outputHtmlName);
            builder.toStream(os);
            builder.run();
            os.close();
            return null;
//            return fileService.downloadFile(filePdfName);
        } catch (Exception ex) {
            logger.error("Exception occured while processing freeMarker template: {} ", ex.getMessage(), ex);
        }
        return null;
    }

    private void defaultInput(Map<String, Object> input) {
        input.put("tfr", "");
        input.put("createdDate", "");
        input.put("createLoanSugMinute", "");
        input.put("mnRateRevTermUnit", "");
        input.put("cifNo", "");
        input.put("customerIbName", "");
        input.put("mnRateRevTerm", "");
        input.put("contractNo", "");
        input.put("dueDate", "");
        input.put("floatInfo", ""); // id
        input.put("paymentDomestic", "");
        input.put("approvalLoanDate", "");
        input.put("dearlerRate", "");
        input.put("paymentSalary", "");
        input.put("numberAmount", "");
        input.put("varianceCode", "");
        input.put("accountNo", "");
        input.put("disbursementPurpose", "");
        input.put("disbursementDate", "");
        input.put("issueDate", "");
        input.put("numberAmountCus", "");
        input.put("disbursementHour", "");
        input.put("issuePlace", "");
        input.put("createLoanSugDate", "");
        input.put("customerIdNo", "");
        input.put("lastDueDate", "");
        input.put("interestRate", "");
        input.put("isFlat", "");
        input.put("margin", "");
        input.put("flatInfo", "");
        input.put("wordAmount", "");
        input.put("createLoanSugHour", "");
        input.put("totalAmoutApprove", "");
        input.put("paymentLC", "");
        input.put("isFloat", "");
        input.put("wordAmountCus", "");
        input.put("taxCode", "");
        input.put("customerName", "");
        input.put("disbursementMinute", "");
        input.put("convertDrbDoc", "");
        input.put("convertDrbDocId", "");
        input.put("sugsNo", "");
        input.put("loanTerm", "");
        input.put("productCode", "");
        input.put("productCode", "");
        input.put("mnRateReviewDate", "");
        input.put("csh", "");
        input.put("creditFee", "");
        input.put("effectiveDate", "");
        input.put("customerNamePay", "");
        input.put("floatInfoBody", "");
        input.put("showApproveLoanSuggestion", "");
        input.put("finalSchedules", "");
        input.put("noSchedules", "");
        input.put("noSchedulesBody", "");
        input.put("hasSchedulesTableBody", "");
        input.put("hasSchedules", "");
        input.put("hasSchedulesTable", "");
        input.put("finalSchedulesRoot", "");
        input.put("documentRefund", "");
        input.put("fundamentalPaymentTable", "");
        input.put("fundamentalPaymentTableSal", "");
        input.put("fundamentalPaymentTableLc", "");
        input.put("cusCertCode", "");
        input.put("limitName", "");
        input.put("GTCK", "");
        input.put("GTDHK", "");
        input.put("GTKDHK", "");
        input.put("GLTCK", "");
        input.put("rootFixLoanTermCode", "");
        input.put("intFreq", "");
        input.put("prFreq", "");
        input.put("maturityDate", "");
        input.put("prMaturityDate", "");
        input.put("gtkdhkTable", "");
        input.put("gtkdhkTableBody", "");
        input.put("username", "");
    }
}
