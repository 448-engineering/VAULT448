use std::time::Duration;

use async_dup::Arc;
use dir_meta::smol::{
    channel::{bounded, Receiver, Sender},
    stream::StreamExt,
};
use once_cell::sync::Lazy;

pub type ArcSender = Arc<Sender<u64>>;
pub type ArcReceiver = Arc<Receiver<u64>>;

pub(crate) static APP: Lazy<App> = Lazy::new(|| App::new());

pub(crate) struct App {
    sender: ArcSender,
    receiver: ArcReceiver,
}

impl App {
    pub fn new() -> Self {
        let (sender, receiver) = bounded::<u64>(1000);
        let sender = Arc::new(sender);
        let receiver = Arc::new(receiver);

        Self { sender, receiver }
    }

    pub async fn run(&mut self) {
        let mut counter = 0u64;
        while let Some(_) = smol::Timer::interval(Duration::from_secs(3)).next().await {
            counter += 1;

            self.sender.send(counter).await.unwrap();
        }
    }
}
