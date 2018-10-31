import UIKit
import data

class ViewController: UIViewController {
    let api = HNApi()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        label.text = Proxy().proxyHello()
        
        api.fetchTopStoryIds { (ids) in
            NSLog("ids: \(ids.count)")
            self.api.fetchStories(ids: ids[0..<10].map { $0 }, callback: { (stories: [Story]) in
                NSLog("stories: \(stories.count)")
                self.label.text = stories[0].title
                return KotlinUnit()
            })
            return KotlinUnit()
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    @IBOutlet weak var label: UILabel!
}
