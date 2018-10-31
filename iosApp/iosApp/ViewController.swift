import UIKit
import data

class ViewController: UIViewController {
    let api = HNApi()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        label.text = Proxy().proxyHello()
        
        api.fetchTopStories { (ids) in
            self.label.text = ids
            return KotlinUnit()
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    @IBOutlet weak var label: UILabel!
}
