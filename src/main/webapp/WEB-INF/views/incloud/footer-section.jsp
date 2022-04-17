<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <footer id="footer">

    <div class="footer-top">
      <div class="container">
        <div class="row">

          <div class="col-lg-3 col-md-6 footer-contact">
            <h3>Mentor</h3>
            <p>
              <strong>地址:</strong>
              高雄市  前金區<br>
              中正四路211號8樓之1<br>
              <strong>電話:</strong> 07 XXX XXXX<br>
              <strong>信箱:</strong> xxx@example.com<br>
            </p>
          </div>

          <div class="col-lg-2 col-md-6 footer-links">
            <h4>連結</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="#">首頁</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">關於我們</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">隱私權政策</a></li>
            </ul>
          </div>


          <div class="col-lg-4 col-md-6 footer-newsletter">
            <h4>訂閱</h4>
            <p>Tamen quem nulla quae legam multos aute sint culpa legam noster magna</p>
              <input type="email" id="submail" name="email"><input id="addSubscript" type="button" value="Subscribe">
            <script>
            $(document).ready(function() {
            	$('input#addSubscript').click(function() {
            		$.ajax({
            			type: "GET",
            			url: "/SpecialTopic/addSubscript?email=" + $('input#submail').val(),
            			success: function(data) {
            				if (data == "success") {
            					alert("感謝訂閱");
            				}
            			}
            		})
            	})
            })
            </script>
          </div>

        </div>
      </div>
    </div>

    <div class="container d-md-flex py-4">

      <div class="me-md-auto text-center text-md-start">
        <div class="copyright">
          &copy; Copyright <strong><span>Mentor</span></strong>. All Rights Reserved
        </div>
        <div class="credits">
          <!-- All the links in the footer should remain intact. -->
          <!-- You can delete the links only if you purchased the pro version. -->
          <!-- Licensing information: https://bootstrapmade.com/license/ -->
          <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/mentor-free-education-bootstrap-theme/ -->
          Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div>
      </div>
      <div class="social-links text-center text-md-right pt-3 pt-md-0">
        <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
        <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
        <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
        <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
        <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
      </div>
    </div>
  </footer><!-- End Footer -->