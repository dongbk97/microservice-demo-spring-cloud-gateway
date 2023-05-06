<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
  <title>Test FreeMarker</title>
  <style>
    @font-face {
        font-family: 'Roboto';
        src: url('${fontFamily}');
    }

    * {
        font-family: 'Roboto';
    }
    body {
      font-size: 12px;
    }

    .body td {
        border: 1px solid black
    }
    .d-none {
      display: none !important;
    }
    .text-left {
      text-align: left;
    }
    .text-center {
      text-align: center;
    }
    .text-right {
      text-align: right;
    }
    td {
      height: 30px;
      padding-bottom: 5px;
    }
    p {
      font-weight: bolder;
    }
    .ml-10 {
      margin-left: 10px;
    }
    .text-justify {
      text-align: justify;
      text-justify: inter-word;
    }

</style>
</head>
<body>
  <div style="margin: auto; width: 90%; display:block;">
    <table style="width: 100%; border-collapse: collapse; table-layout: fixed;">
      <colgroup>
      </colgroup>
      <tbody> 
        <tr>
          <td colspan="12">
            <img style="height: 40px;" src="data:image/jpeg;base64,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"></img>
          </td>
          <td colspan="12">
            <p style="color: red; font-weight: bold;text-align: right; font-size: 0.8rem">
              Test FreeMarker
            </p>
            <p style="text-align: right; font-size: 0.8rem">
              Hà Nội
            </p>
            <p style="text-align: right; font-size: 0.8rem">
              Tel: 0123456789 - Fax: 0123456789
            </p>
          </td>
        </tr>
        <tr>
          <td colspan="24">
            <h2 style="font-weight: bold; text-align: center">FreeMarker</h2>
            <h4 style="font-weight: bold; text-align: center; font-style: italic;">Số: ${sugsNo} , Ngày: ${createdDate}</h4>
          </td>
        </tr>
        <tr>
          <td colspan="24">
            <h3 style="font-weight: bold;">GIẢI NGÂN</h3>
          </td>
        </tr>
        <tr>
          <td colspan="24">
            <p style="line-height: 20px; font-style: italic;">
              Căn cứ Hợp đồng số ${contractNo} ${effectiveDate} giữa A và ${customerName} (KH) và các Phụ lục kèm theo:
            </p>
          </td>
        </tr>
        <tr>
          <td style="border: 0.5px dashed black" colspan="13">
            <p style="margin-left: 10px;">
              <b>Đơn vị: ${customerName}</b>
            </p>
          </td>
          <td style="border: 0.5px dashed black;" colspan="11">
            <p style="margin-left: 10px;">
              Mã KH (CIF): ${cifNo}
            </p>
          </td>
        </tr>
        <tr style="border-top: 0.5px dashed black;">
          <td style="border: 0.5px dashed black" colspan="13" rowspan="2">
            <p style="margin-left: 10px;">
              Giấy chứng nhận đăng ký doanh nghiệp/Quyết định thành lập số: ${customerIdNo}
            </p>
          </td>
          <td style="border: 0.5px dashed black; height: 30px;" colspan="11">
            <p style="margin-left: 10px;">
              Do: ${issuePlace}
            </p>
          </td>
        </tr>
        <tr>
          <td style="border: 0.5px dashed black; height: 30px;" colspan="11">
            <p style="margin-left: 10px;">
              cấp ngày: ${issueDate}
            </p>
          </td>
        </tr>
        <tr>
          <td style="border: 0.5px dashed black" colspan="11">
            <b style="margin-left: 10px;">Hạn mức giải ngân:</b>
          </td>
          <td style="border: 0.5px dashed black" colspan="13">
            <p style="margin-left: 10px;"> ${limitName}</p>
          </td>
        </tr>
        <tr>
          <td style="border: 0.5px dashed black" colspan="11">
            <b style="margin-left: 10px;">Mục đích giải ngân:</b>
          </td>
          <td style="border: 0.5px dashed black" colspan="13">
            <p style="margin-left: 10px;">Phục vụ sản xuất kinh doanh – ${disbursementPurpose}</p>
          </td>
        </tr>

        <tr>
          <td style="border: 0.5px dashed black" colspan="11">
            <b style="margin-left: 10px;">Ngày giải ngân:</b>
          </td>
          <td style="border: 0.5px dashed black" colspan="13">
            <p style="margin-left: 10px;">${createLoanSugHour} giờ ${createLoanSugMinute} phút ngày ${createLoanSugDate}</p>
            <div class="ml-10">Thời điểm giải ngân thực tế có thể thực hiện trong ngày làm việc tiếp theo</div>
          </td>
        </tr>
        <tr>
          <td style="border: 0.5px dashed black" colspan="11">
            <b style="margin-left: 10px;">Thời hạn vay:</b>
          </td>
          <td style="border: 0.5px dashed black" colspan="13">
            <p style="margin-left: 10px;"> ${loanTerm}</p>
          </td>
        </tr>
      </tbody>
    </table>
    
  </div>

  <!-- Trang 2 -->
  
  <!-- Trang 3 -->

  
  <!-- Trang 4 -->
  
  <!-- Trang 4 -->

  <!-- Trang 5 -->

  

</body>

</html>