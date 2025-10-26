@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Account account) {
        // logic to save user
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Account account) {
        // logic to check user
    }
}
